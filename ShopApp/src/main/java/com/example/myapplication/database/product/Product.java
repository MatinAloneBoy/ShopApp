package com.example.myapplication.database.product;


import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Product {

    @PrimaryKey
    @ColumnInfo(name = "ID")
    public int ID;

    @ColumnInfo(name = "SellerName")
    public String SellerName;

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "PhoneNumber")
    public int PhoneNumber;

    @ColumnInfo(name = "PhotoUrl")
    public String PhotoURL;

    @ColumnInfo(name = "Date")
    public String Date;


    public Product(int ID, String SellerName, String Name, int PhoneNumber, String PhotoURL, String Date) {
        this.ID = ID;
        SellerName = SellerName;
        Name = Name;
        PhoneNumber = PhoneNumber;
        PhotoURL = PhotoURL;
        this.Date = Date;
    }
}
