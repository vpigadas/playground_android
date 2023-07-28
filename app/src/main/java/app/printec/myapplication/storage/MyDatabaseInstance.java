package app.printec.myapplication.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class MyDatabaseInstance extends RoomDatabase {
    abstract UserDao getUserDao();
}
