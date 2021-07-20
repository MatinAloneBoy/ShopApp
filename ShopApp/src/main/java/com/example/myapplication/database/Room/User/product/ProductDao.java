package com.example.myapplication.database.Room.User.product;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("INSERT INTO Product(Name,PhotoUrl,SellerName,SellerPhone,Price,Date,Description) VALUES(:Name,:PhotoUrl,:SellerName,:SellerPhone,:Price,:Date,:Description)")
    void insert(String Name, String PhotoUrl, String SellerName, String SellerPhone, int Price, String Date, String Description);


    @Query("SELECT * FROM Product")
    List<Product> getAll();


    @Query("SELECT * FROM Product WHERE Name IN (:ProductNames)")
    List<Product> loadAllByNames(String[] ProductNames);


    @Query("SELECT * FROM product WHERE Name LIKE :name LIMIT 1")
    Product findByName(String name);


    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);


}