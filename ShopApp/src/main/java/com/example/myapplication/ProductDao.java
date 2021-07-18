package com.example.myapplication;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.UsersDataBase;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("INSERT INTO Product VALUES(:ID,:SellerName,:Name,:PhoneNumber,:PhotoUrl,:Date)")
    void insert(int ID, String SellerName, String Name, int PhoneNumber, String PhotoUrl, String Date);


    @Query("SELECT * FROM Product")
    List<Product> getAll();


    @Query("SELECT * FROM Product WHERE Name IN (:ProductNames)")
    List<Product> loadAllByNames(int[] ProductNames);


    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);

}
