package com.mahmoud.mohammed.onlineshopping.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mohammed on 09/12/2017.
 */

public class DbSqlite extends SQLiteOpenHelper {
    private static String databaseName="database";
    SQLiteDatabase database;
    public DbSqlite(Context context)
    {
        super(context,databaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Customers(CustID integer primary key autoincrement ,name text ,username text ,password text,gender text ,birthdate text, jop text ,rememberMe integer)");
        db.execSQL("create table Categories(CatID integer primary key autoincrement ,name text )");
        db.execSQL("create table Products(ProID integer primary key autoincrement ," +
                "Name text not null,Title text not null,Phone text not null," +
                "Price text not null," +
                "Quantity integer ," +
                "Cat_id integer,Foreign Key(Cat_id)REFERENCES Categories(CatID))");

        db.execSQL("create table Orders(OrdID integer primary key autoincrement ," +
                "date text not null,address text not null," +
                "Cust_id integer,Foreign Key(Cust_id)REFERENCES Customers(CustID))");

        db.execSQL("create table OrderDetails(quantity integer," +
                "Ord_id integer,Pro_id integer,Foreign Key(Ord_id)REFERENCES Orders(OrdID)," +
                "Foreign Key(Pro_id)REFERENCES Products(ProID)," +
                "primary key (Ord_id, Pro_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists  Customers");
        db.execSQL("drop table if exists  Categories");
        db.execSQL("drop table if exists  Products");
        db.execSQL("drop table if exists  Orders");
        db.execSQL("drop table if exists  OrderDetails");
        onCreate(db);
    }
}
