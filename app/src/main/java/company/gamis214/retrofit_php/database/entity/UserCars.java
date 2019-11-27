package company.gamis214.retrofit_php.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "user_cars",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id_user",
                childColumns = "id_car_user",
                onDelete = CASCADE,
                onUpdate = CASCADE))
public class UserCars {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_car")
    private int idCar;

    @ColumnInfo(name = "car_name")
    private String carName;

    @ColumnInfo(name = "car_model")
    private String carModel;

    @ColumnInfo(name = "car_year")
    private String carYear;

    @ColumnInfo(name = "id_car_user")
    private String idUser;

    public UserCars(String carName, String carModel, String carYear, String idUser) {
        this.carName = carName;
        this.carModel = carModel;
        this.carYear = carYear;
        this.idUser = idUser;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
