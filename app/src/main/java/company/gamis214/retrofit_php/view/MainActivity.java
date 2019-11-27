package company.gamis214.retrofit_php.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import company.gamis214.retrofit_php.R;
import company.gamis214.retrofit_php.adatpters.CustomAdapter;
import company.gamis214.retrofit_php.database.UsersDataBase;
import company.gamis214.retrofit_php.database.entity.User;
import company.gamis214.retrofit_php.database.entity.UserCars;
import company.gamis214.retrofit_php.database.joins.UserCarsDetail;
import company.gamis214.retrofit_php.services.CarsUserList;
import company.gamis214.retrofit_php.services.ResponseCarsUser;
import company.gamis214.retrofit_php.services.ResponseUserBody;
import company.gamis214.retrofit_php.services.RetrofitController;
import company.gamis214.retrofit_php.services.RetrofitInterface;
import company.gamis214.retrofit_php.services.UsersResponse;
import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn,btnShowData,btnClear,btnInnerShowData,btnInsertUser;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private static final int GET_USERS = 0, GET_USER_CARS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btnShowData  = findViewById(R.id.btnShowData);
        btnInnerShowData  = findViewById(R.id.btnInnerShowData);
        btnClear = findViewById(R.id.btnClear);
        btnInsertUser = findViewById(R.id.btnInsertUser);
        recyclerView = findViewById(R.id.recyclerView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeServices(GET_USERS);
            }
        });
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDataFromDB();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDB();
            }
        });
        btnInnerShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInnerJoinData();
            }
        });
        btnInsertUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUser();
            }
        });
    }

    private void executeServices(int typeServices){
        RetrofitInterface retrofitInterface = new RetrofitController().getInstanceRetrofit().create(RetrofitInterface.class);
        switch (typeServices){
            case GET_USERS:
                retrofitInterface.getUserData(typeServices)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Response<ResponseUserBody>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Response<ResponseUserBody> requestBodyResponse) {
                                saveDataUserInDB(requestBodyResponse.body().getUsers());
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case GET_USER_CARS:
                retrofitInterface.getCarsUserData(typeServices)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Response<ResponseCarsUser>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Response<ResponseCarsUser> requestBodyResponse) {
                                saveDataCarsUserInDB(requestBodyResponse.body().getCarsUserList());
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

    private void saveDataUserInDB(List<UsersResponse> lstUsersResponses){
        List<User> lstUser = new ArrayList<>();

        for (UsersResponse itemUsersResponse : lstUsersResponses) {
            lstUser.add(new User(Integer.parseInt(itemUsersResponse.getId()),itemUsersResponse.getName(),itemUsersResponse.getLastName(),itemUsersResponse.getEmail()));
        }

        UsersDataBase.getInstance(this).getUserDao().insertUsers(lstUser)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        executeServices(GET_USER_CARS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void saveDataCarsUserInDB(List<CarsUserList> carsUserList){
        List<UserCars> lstCars = new ArrayList<>();
        for (CarsUserList itemCarResponse : carsUserList) {
            lstCars.add(new UserCars(
                    itemCarResponse.getNameCar(),
                    itemCarResponse.getModelCar(),
                    itemCarResponse.getYear(),
                    itemCarResponse.getIdUser()));
        }
        UsersDataBase.getInstance(this).getUserCarsDao().insertCar(lstCars).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "All data inserted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void showDataFromDB(){
        UsersDataBase.getInstance(this).getUserDao().getAllUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new MaybeObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<User> lstUsers) {
                        setRecyclerView(lstUsers,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }

    private void showInnerJoinData(){
        UsersDataBase.getInstance(this).getUserDao().getJoinDetailUserWithCars().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new MaybeObserver<List<UserCarsDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<UserCarsDetail> userCarsDetails) {
                        setRecyclerView(null,userCarsDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
    }

    private void insertUser(){
        int number = new Random().nextInt(20);
        UsersDataBase.getInstance(this).getUserDao().insertOneUser(
                new User(number,"Test.No->"+number,"LastName->"+number,"email->"+number)
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        showDataFromDB();
                        Toast.makeText(MainActivity.this, "INSERT CORRECT", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void clearDB(){
        customAdapter.cleanRecyclerViewData();
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter){
                UsersDataBase.getInstance(getApplicationContext()).runInTransaction(new Runnable() {
                    @Override
                    public void run() {
                        UsersDataBase.getInstance(getApplicationContext()).getUserDao().deleteAllUsers();
                        UsersDataBase.getInstance(getApplicationContext()).getUserCarsDao().deleteAllCarsUser();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }

    private void setRecyclerView(List<User> lstUsers,List<UserCarsDetail> lstCarsUser){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        customAdapter = new CustomAdapter(lstUsers,lstCarsUser,this);
        recyclerView.setAdapter(customAdapter);
    }

}
