package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;



public class productDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="ProductDataBase";
    private static final String TABLE_NAME="product_DATA";
    private static final String COL_1="ID";
    private static final String COL_2="Name";
    private static final String COL_3="SellerName";
    private static final String COL_4="SellerPhoneNumber";
    private static final String COL_5="PRODUCT_IMAGE_PATH";
    private static final String COL_6="Price";
    private static final String COL_7="Categoty";



    public productDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SellerName TEXT,SellerPhoneNumber TEXT,USER_IMAGE_PATH TEXT,Price TEXT,Categoty TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean register_product(String Name,String SellerName,String SellerPhoneNumber,String PRODUCT_IMAGE_PATH,String Price,String Category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,Name);
        values.put(COL_3,SellerName);
        values.put(COL_4,SellerPhoneNumber);
        values.put(COL_5,PRODUCT_IMAGE_PATH);
        values.put(COL_6,Price);
        values.put(COL_7,Category);
        long result = db.insert(TABLE_NAME,null,values);
        if(result==-1){
            return false;

        }else{

            return true;
        }
    }
    public boolean check_product(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { COL_1 };
        String selection = COL_3+"=?"+" and "+COL_4+"=?";
        String [] selectionargs = { email , password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if(count>0){

            return true;
        }
        else{
            return false;
        }
    }
}





