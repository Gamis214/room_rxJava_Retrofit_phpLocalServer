package company.gamis214.retrofit_php.adatpters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import company.gamis214.retrofit_php.R;
import company.gamis214.retrofit_php.database.UsersDataBase;
import company.gamis214.retrofit_php.database.entity.User;
import company.gamis214.retrofit_php.database.entity.UserCars;
import company.gamis214.retrofit_php.database.joins.UserCarsDetail;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> lstUsers;
    private List<UserCarsDetail> lstUsersCars;
    private Activity context;

    public CustomAdapter(List<User> lstUsers, List<UserCarsDetail> lstUsersCars, Activity context){
        this.lstUsers = lstUsers;
        this.lstUsersCars = lstUsersCars;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(lstUsers != null){
            ((ItemHolder)holder).bindUserView();
        }else{
            ((ItemHolder)holder).bindCarsUserView();
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtId,txtName,txtLastName,txtEmail;
        private Button btnClear;
        private LinearLayout containerCars;

        public ItemHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            containerCars = itemView.findViewById(R.id.containerCars);
            btnClear = itemView.findViewById(R.id.btnClear);
            btnClear.setOnClickListener(this);
        }

        public void bindUserView(){
            txtId.setText(Integer.toString(lstUsers.get(getAdapterPosition()).getIdUser()));
            txtName.setText(lstUsers.get(getAdapterPosition()).getName());
            txtLastName.setText(lstUsers.get(getAdapterPosition()).getLastName());
            txtEmail.setText(lstUsers.get(getAdapterPosition()).getEmail());
        }

        public void bindCarsUserView(){
            txtId.setText(Integer.toString(lstUsersCars.get(getAdapterPosition()).getUser().getIdUser()));
            txtName.setText(lstUsersCars.get(getAdapterPosition()).getUser().getName());
            txtLastName.setText(lstUsersCars.get(getAdapterPosition()).getUser().getLastName());
            txtEmail.setText(lstUsersCars.get(getAdapterPosition()).getUser().getEmail());
            for (UserCars itemCar:lstUsersCars.get(getAdapterPosition()).getCarsList()) {
                View view = context.getLayoutInflater().inflate(R.layout.item_car,null);
                TextView txtIdCar = view.findViewById(R.id.txtIdCar),
                         txtNameCar = view.findViewById(R.id.txtNameCar),
                         txtModelCar = view.findViewById(R.id.txtModelCar),
                         txtYearCar = view.findViewById(R.id.txtYearCar);

                txtIdCar.setText(Integer.toString(itemCar.getIdCar()));
                txtNameCar.setText(itemCar.getCarName());
                txtModelCar.setText(itemCar.getCarModel());
                txtYearCar.setText(itemCar.getCarYear());
                containerCars.addView(view);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnClear:
                    UsersDataBase.getInstance(context).getUserDao()
                            .deleteUser(lstUsers != null ?
                                    lstUsers.get(getAdapterPosition()).getIdUser() :
                                    lstUsersCars.get(getAdapterPosition()).getUser().getIdUser())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    if(lstUsers != null){
                                        lstUsers.remove(getAdapterPosition());
                                    } else {
                                        lstUsersCars.remove(getAdapterPosition());
                                    }
                                    notifyDataSetChanged();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                    break;
            }
        }
    }

    public void cleanRecyclerViewData(){
        if(lstUsers != null){
            lstUsers.clear();
        }else{
            lstUsersCars.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(lstUsers != null){
            return lstUsers.size();
        }else{
            return lstUsersCars.size();
        }
    }

}
