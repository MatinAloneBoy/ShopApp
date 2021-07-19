package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UpdateProfileDataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "UserDataBase.db";
    private static final String TABLE_NAME = "USER_DATA";
    private static final String COL_1="ID";
    private static final String COL_2="USERNAME";
    private static final String COL_3="EMAIL";
    private static final String COL_4="PASSWORD";
    private static final String COL_5="USER_IMAGE_PATH";
    private static final String COL_6="NUM_LOGIN";
    private static final String COL_7="PhoneNumber";
    private static final String COL_8="Type";
    private static final String COL_9="Product_Num";


    public UpdateProfileDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,EMAIL TEXT,PASSWORD TEXT,USER_IMAGE_PATH TEXT,NUM_LOGIN TEXT,PhoneNumber TEXT,Type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public void updateName(String name,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE USER_DATA SET USERNAME = "+name+" WHERE Email = "+Email,null);

    }
    public void updatePhoneNumber(String phone,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE USER_DATA SET PhoneNumber = "+phone+" WHERE Email = "+Email,null);

    }
    public void updatePassword(String passw,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE USER_DATA SET PASSWORD = "+passw+" WHERE Email = "+Email,null);

    }
    public void updateProfilePhoto(String path,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE USER_DATA SET USER_IMAGE_PATH = "+path+" WHERE Email = "+Email,null);

    }
    public void updateType(String type,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE USER_DATA SET Type = "+type+" WHERE Email = "+Email,null);

    }
    public String readProductNum(String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        String s=db.rawQuery("SELECT Product_Num FROM Product WHERE Name IN (:ProductNames)",null).toString();
        return s;
    }

    public void updateProductNum(String num,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE USER_DATA SET Product_Num = "+num+" WHERE Email = "+Email,null);

    }
}
