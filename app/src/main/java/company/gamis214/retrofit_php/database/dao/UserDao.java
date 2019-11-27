package company.gamis214.retrofit_php.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import company.gamis214.retrofit_php.database.entity.User;
import company.gamis214.retrofit_php.database.joins.UserCarsDetail;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {

    //TODO Usar RXJava para Insertar, Actualizar y Borrar
    //TODO LiveData para consultas

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUsers(List<User> lstUsers);

    @Query("SELECT * FROM user_table")
    Maybe<List<User>> getAllUsers();

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("DELETE FROM user_table WHERE id_user=:idUser")
    Completable deleteUser(int idUser);

    @Query("SELECT * FROM user_table")
    Maybe<List<UserCarsDetail>> getJoinDetailUserWithCars();

    /*@Query("SELECT * FROM user_table WHERE id_user=:idUser")
    Single<User> getUser(int idUser);*/

}
