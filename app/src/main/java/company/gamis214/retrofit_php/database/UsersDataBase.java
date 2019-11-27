package company.gamis214.retrofit_php.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import company.gamis214.retrofit_php.database.dao.UserCarsDao;
import company.gamis214.retrofit_php.database.dao.UserDao;
import company.gamis214.retrofit_php.database.entity.UserCars;
import company.gamis214.retrofit_php.database.entity.User;

@Database(entities = {User.class, UserCars.class},version = 1)
public abstract class UsersDataBase extends RoomDatabase {

    private static final String DB_NAME = "userTest.db";
    private static volatile UsersDataBase instance;

    public static synchronized UsersDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static UsersDataBase create(final Context context) {
        return Room.databaseBuilder(
                context,
                UsersDataBase.class,
                DB_NAME).build();
    }

    public abstract UserDao getUserDao();
    public abstract UserCarsDao getUserCarsDao();

}
