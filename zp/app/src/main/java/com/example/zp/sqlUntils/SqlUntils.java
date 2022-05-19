package com.example.zp.sqlUntils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.zp.TalkEntity;
import com.example.zp.TdInfo;
import com.example.zp.ZpInfoEntity;
import com.example.zp.ZpUser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class SqlUntils extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQLite.db";
    private SQLiteDatabase sqLiteDatabase, sqLiteDatabaseWritable;

    private static final String CREATE_TABLE_SQL_ZPINFO = "CREATE TABLE \"zpinfo\" (\n" +
            "  \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"salary\" TEXT,\n" +
            "  \"place\" TEXT,\n" +
            "  \"ltdid\" integer,\n" +
            "  \"ltdname\" TEXT,\n" +
            "  \"job\" TEXT,\n" +
            "  \"onespeak\" TEXT\n" +
            ");";
    private static final String CREATE_TABLE_SQL_ZPUSER = "CREATE TABLE \"zpuser\" (\n" +
            "  \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"username\" TEXT,\n" +
            "  \"password\" TEXT,\n" +
            "  \"onespeak\" TEXT,\n" +
            "  \"skill\" TEXT\n," +
            "  \"xl\" TEXT\n," +
            "  \"byyx\" TEXT\n," +
            "  \"age\" TEXT\n" +
            ")";
    private static final String CREATE_TABLE_SQL_TDINFO = "CREATE TABLE \"tdinfo\" (\n" +
            "  \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"job\" text,\n" +
            "  \"username\" TEXT,\n" +
            "  \"place\" text,\n" +
            "  \"ltdname\" TEXT,\n" +
            "  \"salary\" text,\n" +
            " \"date\" TEXT" +
            ");";
    private static final String CREATE_TABLE_SQL_TALK = "CREATE TABLE \"talk\" (\n" +
            "  \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"ltdname\" TEXT,\n" +
            "  \"tdid\" INTEGER,\n" +
            "  \"myTalk\" TEXT,\n" +
            "  \"qyoruser\" integer\n" +
            ");";
    public SqlUntils(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SqlUntils(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        sqLiteDatabase = getReadableDatabase();
        sqLiteDatabaseWritable = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL_ZPUSER);
        db.execSQL(CREATE_TABLE_SQL_ZPINFO);
        db.execSQL(CREATE_TABLE_SQL_TDINFO);
        db.execSQL(CREATE_TABLE_SQL_TALK);
    }

    public void initInfo(SQLiteDatabase db) {
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('18~22k', '北京', 1, '京东', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('17~22k', '北京', 2, '字节跳动', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('12~22k', '北京', 3, '百度', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('15~20k', '北京', 3, '阿里', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('18~22k·13薪', '北京', 1, '京东', '测试', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('4~8k', '北京', 1, '京东', '产品', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('11~12k', '北京', 3, '阿里', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('18~22k', '北京', 1, '京东', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('18~22k·15薪', '上海', 3, '阿里', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('16~18k', '北京', 2, '字节跳动', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('18~22k', '杭州', 3, '阿里', '开发', '拒绝996')");
        db.execSQL("INSERT INTO \"zpinfo\" (\"salary\", \"place\", \"ltdid\", \"ltdname\", \"job\", \"onespeak\") VALUES ('18~20k·14薪', '北京', 1, '京东', '开发', '拒绝996')");

    }

    public void onCreate() {
        try {
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @SuppressLint("Range")
    public List<ZpInfoEntity> qryZpinfo(String job) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from zpinfo where job = ?", new String[]{job});
        List<ZpInfoEntity> list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ZpInfoEntity zpinfo = new ZpInfoEntity();
                zpinfo.setId(cursor.getString(cursor.getColumnIndex("id")));
                zpinfo.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                zpinfo.setPlace(cursor.getString(cursor.getColumnIndex("place")));
                zpinfo.setLtdid(cursor.getString(cursor.getColumnIndex("ltdid")));
                zpinfo.setLtdname(cursor.getString(cursor.getColumnIndex("ltdname")));
                zpinfo.setJob(cursor.getString(cursor.getColumnIndex("job")));
                zpinfo.setOnespeak(cursor.getString(cursor.getColumnIndex("onespeak")));
                list.add(zpinfo);
            }
        } else {
            initInfo(sqLiteDatabase);
            cursor = sqLiteDatabase.rawQuery("select * from zpinfo where job = ?", new String[]{job});
            while (cursor.moveToNext()) {
                ZpInfoEntity zpinfo = new ZpInfoEntity();
                zpinfo.setId(cursor.getString(cursor.getColumnIndex("id")));
                zpinfo.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                zpinfo.setPlace(cursor.getString(cursor.getColumnIndex("place")));
                zpinfo.setLtdid(cursor.getString(cursor.getColumnIndex("ltdid")));
                zpinfo.setLtdname(cursor.getString(cursor.getColumnIndex("ltdname")));
                zpinfo.setJob(cursor.getString(cursor.getColumnIndex("job")));
                zpinfo.setOnespeak(cursor.getString(cursor.getColumnIndex("onespeak")));
                list.add(zpinfo);
            }
        }
        return list;


    }

    @SuppressLint("Range")
    public List<ZpInfoEntity> qryZpinfoByltdname(String ltdname) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from zpinfo where ltdname like ?", new String[]{ltdname});
        List<ZpInfoEntity> list = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ZpInfoEntity zpinfo = new ZpInfoEntity();
                zpinfo.setId(cursor.getString(cursor.getColumnIndex("id")));
                zpinfo.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                zpinfo.setPlace(cursor.getString(cursor.getColumnIndex("place")));
                zpinfo.setLtdid(cursor.getString(cursor.getColumnIndex("ltdid")));
                zpinfo.setLtdname(cursor.getString(cursor.getColumnIndex("ltdname")));
                zpinfo.setJob(cursor.getString(cursor.getColumnIndex("job")));
                zpinfo.setOnespeak(cursor.getString(cursor.getColumnIndex("onespeak")));
                list.add(zpinfo);
            }
        }
        return list;


    }

    @SuppressLint("Range")
    public ZpUser login(String userName, String passwordName) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from zpuser where username = ? and password = ?", new String[]{userName, passwordName});
        ZpUser zpinfo = new ZpUser();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                zpinfo.setId(cursor.getString(cursor.getColumnIndex("id")));
                zpinfo.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                zpinfo.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                zpinfo.setSkill(cursor.getString(cursor.getColumnIndex("skill")));
                zpinfo.setOnespeak(cursor.getString(cursor.getColumnIndex("onespeak")));
            }
        }
        return zpinfo;
    }

    public void inserZpuser(ZpUser zpUser) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", zpUser.getUsername());
        contentValues.put("password", zpUser.getPassword());
        contentValues.put("skill", zpUser.getSkill());
        contentValues.put("onespeak", zpUser.getOnespeak());
        contentValues.put("byyx", zpUser.getByyx());
        contentValues.put("age", zpUser.getAge());
        contentValues.put("xl", zpUser.getXl());
        sqLiteDatabase.insert("zpuser", null, contentValues);
    }

    @SuppressLint("Range")
    public ZpUser getUserInfo(String userName) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from zpuser where username = ? ", new String[]{userName});
        ZpUser zpinfo = new ZpUser();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                zpinfo.setId(cursor.getString(cursor.getColumnIndex("id")));
                zpinfo.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                zpinfo.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                zpinfo.setSkill(cursor.getString(cursor.getColumnIndex("skill")));
                zpinfo.setOnespeak(cursor.getString(cursor.getColumnIndex("onespeak")));
                zpinfo.setByyx(cursor.getString(cursor.getColumnIndex("byyx")));
                zpinfo.setAge(cursor.getString(cursor.getColumnIndex("age")));
                zpinfo.setXl(cursor.getString(cursor.getColumnIndex("xl")));
            }
        }
        return zpinfo;


    }

    public void updUserInfo(String skills, String onespeaks,
                            String byyx, String xl, String age, String userName) {
        ContentValues values = new ContentValues();
        values.put("skill", skills);
        values.put("onespeak", onespeaks);
        values.put("byyx", byyx);
        values.put("xl", xl);
        values.put("age", age);
        sqLiteDatabase.update("zpuser", values, "username = ?", new String[]{userName});

    }

    public void upPassword(String userName, String passwordName) {
        ContentValues values = new ContentValues();
        values.put("password", passwordName);
        sqLiteDatabase.update("zpuser", values, "username = ?", new String[]{userName});

    }

    public long insertTdInfo(TdInfo tdInfo) {
        ContentValues values = new ContentValues();
        values.put("username", tdInfo.getUsername());
        values.put("job", tdInfo.getJob());
        values.put("place", tdInfo.getPlace());
        values.put("ltdname", tdInfo.getLtdname());
        values.put("salary", tdInfo.getSalary());
        values.put("date", tdInfo.getDate());
       return sqLiteDatabase.insert("tdinfo", null, values);
    }

    @SuppressLint("Range")
    public List<TdInfo> qryTdinfo(String userName) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tdinfo where username = ? ", new String[]{userName});
        List<TdInfo> list = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                TdInfo td = new TdInfo();
                td.setId(cursor.getString(cursor.getColumnIndex("id")));
                td.setDate(cursor.getString(cursor.getColumnIndex("date")));
                td.setJob(cursor.getString(cursor.getColumnIndex("job")));
                td.setPlace(cursor.getString(cursor.getColumnIndex("place")));
                td.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                td.setLtdname(cursor.getString(cursor.getColumnIndex("ltdname")));
                td.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                list.add(td);
            }
        }
        return list;
    }

    public void insertTalkInfo(TalkEntity talkEntity) {
        ContentValues values = new ContentValues();
        values.put("myTalk", talkEntity.getMyTalk());
        values.put("tdid", talkEntity.getTdid());
        values.put("ltdname", talkEntity.getLtdname());
        values.put("qyoruser", talkEntity.getQyoruser());
        sqLiteDatabase.insert("talk", null, values);

    }

    @SuppressLint("Range")
    public List<TalkEntity> qryTalk(String tdid) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from talk where tdid = ? ", new String[]{tdid});
        List<TalkEntity> list = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                TalkEntity talk = new TalkEntity();
                talk.setTdid(cursor.getString(cursor.getColumnIndex("tdid")));
                talk.setQyoruser(cursor.getString(cursor.getColumnIndex("qyoruser")));
                talk.setId(cursor.getString(cursor.getColumnIndex("id")));
                talk.setMyTalk(cursor.getString(cursor.getColumnIndex("myTalk")));
                talk.setLtdname(cursor.getString(cursor.getColumnIndex("ltdname")));
                list.add(talk);
            }
        }
        return list;
    }
}
