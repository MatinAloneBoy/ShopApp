package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class User {

    @ColumnInfo
    public int id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String imagePath;


    public User(int id, String name, String email, String imagePath) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imagePath = imagePath;
    }
}
