package com.example.myapplication.database.Room.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UserDao {

    @Query("INSERT INTO User(name,email,password,phoneNum,imagePath) VALUES(:name,:email,:password,:phoneNum,:imagePath)")
    void insert(String name, String email, String password, String phoneNum, String imagePath);


    @Query("SELECT * FROM User")
    List<User> getAll();


    @Query("SELECT * FROM User WHERE name IN (:UserNames)")
    List<User> loadAllByNames(String[] UserNames);


    @Query("SELECT * FROM User WHERE name LIKE :Name LIMIT 1")
    User findByName(String Name);


    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}

