<<<<<<< HEAD
package com.example.myapplication.database.product;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.product.Product;

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
=======
package com.example.myapplication.database.product;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.product.Product;

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
>>>>>>> 46d9c479dfc7ed9c66e29ed55300803775512841
