package com.example.myapplication.database.Room.User;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    public int id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String password;

    @ColumnInfo
    public String phoneNum;

    @ColumnInfo
    public String imagePath;

    public User(String name, String email, String password, String phoneNum, String imagePath) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.imagePath = imagePath;
    }
}
