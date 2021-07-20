package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;



    public class UsersDataBase extends SQLiteOpenHelper {

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


        public UsersDataBase(@Nullable Context context) {
            super(context, DATABASE_NAME,null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,EMAIL TEXT,PASSWORD TEXT,USER_IMAGE_PATH TEXT,NUM_LOGIN TEXT,PhoneNumber TEXT,Type TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);

        }
        public boolean register_user(String username,String email,String password,String type,String PhoneNum,String imapath){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_2,username);
            values.put(COL_3,email);
            values.put(COL_4,password);
            values.put(COL_5,imapath);
            values.put(COL_7,PhoneNum);
            values.put(COL_8,type);
            long result = db.insert(TABLE_NAME,null,values);
            if(result==-1){
                return false;

            }else{

                return true;
            }
        }
        public boolean check_user(String email,String password){
            String[] a1=email.split("@");
            SQLiteDatabase db = this.getReadableDatabase();
            String pass = db.rawQuery("SELECT PASSWORD FROM USER_DATA WHERE EMAIL LIKE :emial LIMIT 1" ,null).toString();
//            String pass=cursor.getString(4);
            db.close();
//            cursor.close();
            if (password.contains(pass)){
                return true;
            }else {
                return false;
            }
        }
    }





