package com.example.myapplication.database.product;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static okhttp3.internal.Internal.instance;

@Database(entities =Product.class, version = 1)
public abstract class ProductDataBase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static final String DB_NAME = "product_db";
    private static ProductDataBase instance;

    public static synchronized ProductDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),ProductDataBase.class,
                    DB_NAME).fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
