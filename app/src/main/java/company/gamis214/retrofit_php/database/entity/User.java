package company.gamis214.retrofit_php.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_user", index = true)
    private int idUser;

    @ColumnInfo(name = "user_name")
    private String name;

    @ColumnInfo(name = "user_last_name")
    private String lastName;

    @ColumnInfo(name = "user_email")
    private String email;

    public User(int idUser, String name, String lastName, String email) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
