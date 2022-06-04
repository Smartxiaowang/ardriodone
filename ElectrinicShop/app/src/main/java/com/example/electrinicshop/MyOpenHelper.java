package com.example.electrinicshop;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//数据库配置类
public class MyOpenHelper extends SQLiteOpenHelper {
    //构造方法
    public MyOpenHelper(@Nullable Context context) {
        super(context, "electronics.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" +
                " electronic( " +
                "\"id\" integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"buyer\" integer,\n" +
                "  \"electronic_name\" TEXT,\n" +
                "  \"electronic_price\" TEXT,\n" +
                "  \"amount\" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertOrder(HashMap<String, String> data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("buyer", "lishi");
        contentValues.put("electronic_name", data.get("electronic_name"));
        contentValues.put("electronic_price", data.get("electronic_price"));
        contentValues.put("amount", data.get("amount"));
        db.insert("electronic", null, contentValues);

    }

}

