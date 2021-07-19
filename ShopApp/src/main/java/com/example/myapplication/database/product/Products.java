package com.example.myapplication.database.product;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class Products extends RoomDatabase {
    public abstract ProductDao productDao();

}
