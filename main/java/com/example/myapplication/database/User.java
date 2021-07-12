package com.example.myapplication.database;

public class User {
    public String Email,Password,UserName;
    public Double PhoneNum;
    enum UserMode{
        Admin,
        Seller,
        Customer
    }

}
