package app.printec.myapplication.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(UserEntity user) throws Exception;

//    @Insert
//    void create(UserEntity user);
//
//    @Insert
//    void create(UserEntity... user);
//
//    @Insert
//    void create(List<UserEntity> user) throws Exception;
//
//    @Update
//    void update(UserEntity user) throws Exception;

    @Delete
    void delete(UserEntity user);

    @NonNull
    @Query("SELECT * FROM User")
    List<UserEntity> read();


    @Nullable
    @Query("SELECT * FROM User LIMIT 1")
    UserEntity readFirst();

    @NonNull
    @Query("SELECT * FROM User WHERE full_name like :search")
    List<UserEntity> read(String search);

    @Nullable
    @Query("SELECT * FROM User WHERE user_id like :id LIMIT 1")
    UserEntity readUser(String id);
}
