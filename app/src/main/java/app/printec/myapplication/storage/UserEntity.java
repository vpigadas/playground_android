package app.printec.myapplication.storage;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "User")
public class UserEntity {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    @NonNull
    private String id;
    @ColumnInfo(name = "full_name")
    private String fullName;
    @ColumnInfo(name = "age")
    private int age;
//    @ColumnInfo(name = "achievements")
//    private List<String> achievements;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public List<String> getAchievements() {
//        return achievements;
//    }
//
//    public void setAchievements(List<String> achievements) {
//        this.achievements = achievements;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, age);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }
}
