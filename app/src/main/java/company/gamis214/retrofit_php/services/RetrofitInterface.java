package company.gamis214.retrofit_php.services;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    /*@GET("/bdTest")
    Call<ResponseUserBody> getUserData(
            @Query("name") String name,
            @Query("lastName") String lastName,
            @Query("typeQuery") int typeQuery
    );*/

    @GET("/bdUser")
    Single<Response<ResponseUserBody>> getUserData(
            @Query("typeQuery") int typeQuery
    );

    @GET("/bdUser")
    Single<Response<ResponseCarsUser>> getCarsUserData(
            @Query("typeQuery") int typeQuery
    );

}
