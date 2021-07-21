package com.example.myapplication.database.Room.User.product;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    public int ID;

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "PhotoUrl")
    public String PhotoUrl;

    @ColumnInfo(name = "SellerName")
    public String SellerName;

    @ColumnInfo(name = "SellerPhone")
    public String SellerPhone;

    @ColumnInfo(name = "Price")
    public int Price;

    @ColumnInfo(name = "Date")
    public String Date;

    @ColumnInfo(name = "Description")
    public String Description;


    public Product(String Name, String PhotoUrl, String SellerName, String SellerPhone, int Price, String Date, String Description) {
        this.Name = Name;
        this.PhotoUrl = PhotoUrl;
        this.SellerName = SellerName;
        this.SellerPhone = SellerPhone;
        this.Price = Price;
        this.Date = Date;
        this.Description = Description;
    }
}
