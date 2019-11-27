package company.gamis214.retrofit_php.database.joins;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import company.gamis214.retrofit_php.database.entity.User;
import company.gamis214.retrofit_php.database.entity.UserCars;

public class UserCarsDetail {

    @Embedded
    public User user;

    @Relation(parentColumn = "id_user",entityColumn = "id_car_user", entity = UserCars.class)
    public List<UserCars> carsList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserCars> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<UserCars> carsList) {
        this.carsList = carsList;
    }
}
