package company.gamis214.retrofit_php.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import company.gamis214.retrofit_php.database.entity.User;
import company.gamis214.retrofit_php.database.entity.UserCars;
import io.reactivex.Completable;

@Dao
public interface UserCarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCar(List<UserCars> lstCars);

    @Query("SELECT * FROM user_cars WHERE id_car_user=:idUser")
    List<UserCars> getCarsFromUser(int idUser);

    @Query("DELETE FROM user_cars")
    void deleteAllCarsUser();

}
