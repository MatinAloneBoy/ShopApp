package com.example.myapplication.database.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.Room.User.product.AppDataBase;
import com.example.myapplication.database.Room.User.product.Product;

import java.util.List;

public class LocalDataSource {


    private AppDataBase db;


    public LocalDataSource(Context context){
        db= Room.databaseBuilder(context,
                AppDataBase.class,"DataBase").build();
    }


    public List<Product> getAllProducts(){
        return db.productDao().getAll();
    }

    public void insertProduct(Product product){
        db.productDao().insertAll(product);
    }


    public List<User> getAllUsers(){
        return db.userDao().getAll();
    }

    public void insertUser(User user){
        db.userDao().insertAll(user);
    }

}
