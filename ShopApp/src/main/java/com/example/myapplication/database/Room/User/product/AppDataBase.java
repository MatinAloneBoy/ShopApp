package com.example.myapplication.database.Room.User.product;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.Room.User.UserDao;

@Database(entities ={Product.class, User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DB_NAME = "products.db";
    private static AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,
                    DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract ProductDao productDao();
    public abstract UserDao userDao();

}
