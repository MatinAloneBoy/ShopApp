package com.example.myapplication.database;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LoginDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="UserDataBaseR.db";
    private static final String TABLE_NAME="USER_DATA";
    private static final String COL_1="ID";
    private static final String COL_2="USERNAME";
    private static final String COL_3="EMAIL";
    private static final String COL_4="PASSWORD";
    private static final String COL_5="USER_IMAGE_PATH";
    private static final String COL_6="NUM_LOGIN";
    private static final String COL_7="PhoneNumber";
    private static final String COL_8="Type";
    static int id = 0;


    public LoginDataBase(@Nullable Context context) {
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

    public int readCourses(String email) {
        String email1 = email;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        id = cursorCourses.getInt(0);
        return id;
    }

}
