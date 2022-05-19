package com.example.xq;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//数据库配置类
public class MyHelper extends SQLiteOpenHelper {
    //构造方法
    public MyHelper(@Nullable Context context) {
        super(context, "xq.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE dremcontentinfo( \"d_id\" integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"u_id\" integer,\n" +
                "  \"dremcontent\" TEXT,\n" +
                "  \"dateup\" TEXT,\n" +
                "  \"starts\" TEXT," +
                "  \"bq\" TEXT)");
        db.execSQL("CREATE TABLE user(u_id INTEGER PRIMARY KEY AUTOINCREMENT ,account VARCHAR(20) UNIQUE,password INTEGER)");
        db.execSQL("cREATE TABLE userinfo( u_id INTEGER NOT NULL, " +
                "  \"nickname\" TEXT,\n" +
                "  \"number\" text,\n" +
                "  \"birsday\" TEXT,\n" +
                "  \"xz\" TEXT,\n" +
                "  \"sex\" TEXT,\n" +
                "  \"jj\" TEXT,\n" +
                "  PRIMARY KEY (\"u_id\")\n" +
                ");");
        db.execSQL("CREATE TABLE \"plp\" (\n" +
                "  \"p_id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"u_id\" INTEGER,\n" +
                "  \"nr\" TEXT,\n" +
                "  \"dateup\" TEXT\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //增删改查的一些操作
    @SuppressLint("Range")
    public List<HomeDataBean> getHomeDataInfo(String uid) {
        SQLiteDatabase db = getWritableDatabase();
        String sql;
        Cursor cursor;
        boolean flag = false;
        if (uid != null && uid.equals("")) {
            flag = true;
            sql = "SELECT\n" +
                    "\tuserinfo.nickname, \n" +
                    "\tuserinfo.jj, \n" +
                    "\tuserinfo.sex, \n" +
                    "\td.d_id, \n" +
                    "\td.dremcontent, \n" +
                    "\td.dateup, \n" +
                    "\td.starts, \n" +
                    "\td.bq\n" +
                    "FROM\n" +
                    "\tdremcontentinfo as d \n" +
                    "\tINNER JOIN\n" +
                    "\tuserinfo\n" +
                    "\tON \n" +
                    "d.u_id = userinfo.u_id";
            cursor = db.rawQuery(sql, new String[]{});
        } else {
            sql = "SELECT\n" +
                    "\tuserinfo.nickname, \n" +
                    "\tuserinfo.jj, \n" +
                    "\tuserinfo.sex\n" +
                    "FROM\n" +
                    "\tuserinfo\n" +
                    "where userinfo.u_id = ?";
            cursor = db.rawQuery(sql, new String[]{uid});
        }

        ArrayList<HomeDataBean> list = new ArrayList<>();
        if (flag) {
            while (cursor.moveToNext()) {
                HomeDataBean homeDataBean = new HomeDataBean();
                homeDataBean.setdId(cursor.getString(cursor.getColumnIndex("d_id")));
                homeDataBean.setJj(cursor.getString(cursor.getColumnIndex("jj")));
                homeDataBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                homeDataBean.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                homeDataBean.setDremcontent(cursor.getString(cursor.getColumnIndex("dremcontent")));
                homeDataBean.setDateup(cursor.getString(cursor.getColumnIndex("dateup")));
                homeDataBean.setBq(cursor.getString(cursor.getColumnIndex("bq")));
                homeDataBean.setStarts(cursor.getString(cursor.getColumnIndex("starts")));
                list.add(homeDataBean);
            }
        } else {
            while (cursor.moveToNext()) {
                HomeDataBean homeDataBean = new HomeDataBean();
                homeDataBean.setJj(cursor.getString(cursor.getColumnIndex("jj")));
                homeDataBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                homeDataBean.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                list.add(homeDataBean);
            }
        }
        return list;
    }

    public void toStarts(String did) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update dremcontentinfo SET starts = starts + 1 WHERE d_id = " + did);

    }

    public void delMessage(String did) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete("dremcontentinfo", "d_id = ?", new String[]{did});
    }

    public void insertDre(String et_fabiao, String bq, String date, String uid) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dremcontent", et_fabiao);
        contentValues.put("bq", bq);
        contentValues.put("dateup", date);
        contentValues.put("u_id", uid);
        contentValues.put("starts", "0");
        db.insert("dremcontentinfo", null, contentValues);
    }

    @SuppressLint("Range")
    public HashMap<String, String> getUserInfo(String uid) {
        HashMap<String, String> data = new HashMap<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from userinfo where u_id = ?", new String[]{uid});
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                data.put("u_id", cursor.getString(cursor.getColumnIndex("u_id")));
                data.put("jj", cursor.getString(cursor.getColumnIndex("jj")));
                data.put("nickname", cursor.getString(cursor.getColumnIndex("nickname")));
                data.put("birsday", cursor.getString(cursor.getColumnIndex("birsday")));
                data.put("xz", cursor.getString(cursor.getColumnIndex("xz")));
                data.put("number", cursor.getString(cursor.getColumnIndex("number")));
                data.put("sex", cursor.getString(cursor.getColumnIndex("sex")));
            }
        }
        return data;
    }

    public void upduserinfo(String uid, String nickname, String number, String jjs, String xzs, String bir) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nickname", nickname);
        contentValues.put("number", number);
        contentValues.put("jj", jjs);
        contentValues.put("xz", xzs);
        contentValues.put("birsday", bir);
        db.update("userinfo", contentValues, "u_id = ? ", new String[]{uid});
    }

    @SuppressLint("Range")
    public List<HashMap<String, String>> getPlpList(String uid, String limt) {
        List<HashMap<String, String>> datalist = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT\n" +
                "\tuserinfo.nickname, \n" +
                "\tuserinfo.sex, \n" +
                "\tuserinfo.jj, \n" +
                "\tplp.p_id, \n" +
                "\tplp.u_id, \n" +
                "\tplp.nr, \n" +
                "\tplp.dateup\n" +
                "FROM\n" +
                "\tplp\n" +
                "\tINNER JOIN\n" +
                "\tuserinfo\n" +
                "\tON \n" +
                "\t\tplp.u_id = userinfo.u_id\n" +
                "WHERE\n" +
                "\tplp.u_id not LIKE ?\n" +
                "ORDER BY\n" +
                "\tRANDOM() ASC\n" +
                "LIMIT ?", new String[]{uid, limt});
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                HashMap<String, String> data = new HashMap<>();
                data.put("u_id", cursor.getString(cursor.getColumnIndex("u_id")));
                data.put("p_id", cursor.getString(cursor.getColumnIndex("p_id")));
                data.put("jj", cursor.getString(cursor.getColumnIndex("jj")));
                data.put("nickname", cursor.getString(cursor.getColumnIndex("nickname")));
                data.put("nr", cursor.getString(cursor.getColumnIndex("nr")));
                data.put("dateup", cursor.getString(cursor.getColumnIndex("dateup")));
                data.put("sex", cursor.getString(cursor.getColumnIndex("sex")));
                datalist.add(data);
            }
        }
        return datalist;
    }

    public void insertPlp(String nr, String date, String uid) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("u_id", uid);
        contentValues.put("nr", nr);
        contentValues.put("dateup", date);
        db.insert("plp", null, contentValues);
    }

}

