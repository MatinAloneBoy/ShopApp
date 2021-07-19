package com.example.myapplication.database.product;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Product {

    @PrimaryKey
    @ColumnInfo(name = "ID")
    public int ID;

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "Group")
    public String Group;

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


    public Product(int ID, String Name, String Group, String PhotoUrl, String SellerName, String SellerPhone, int Price, String Date, String Description) {
        this.ID = ID;
        this.Name = Name;
        this.Group = Group;
        this.PhotoUrl = PhotoUrl;
        this.SellerName = SellerName;
        this.SellerPhone = SellerPhone;
        this.Price = Price;
        this.Date = Date;
        this.Description = Description;
    }
}
