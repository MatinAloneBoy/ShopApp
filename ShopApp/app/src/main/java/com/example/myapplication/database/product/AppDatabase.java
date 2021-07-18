package com.example.myapplication.database.product;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.database.product.Product;
import com.example.myapplication.database.product.ProductDao;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();

}
