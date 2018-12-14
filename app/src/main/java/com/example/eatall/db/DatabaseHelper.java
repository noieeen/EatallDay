package com.example.eatall.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "food";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "foodname";
    public static final String COL_TIME = "time";
    public static final String COL_CAL = "cal";
    public static final String COL_IMAGE = "image";

    private static final String SQL_CREATE_TABLE_PHONE
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_TITLE + " TEXT,"
            + COL_CAL + " TEXT,"
            + COL_IMAGE + " TEXT,"
            + COL_TIME + " TEXT"
            + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PHONE);

        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, "แกงเขียวหวาน");
        cv.put(COL_CAL, "500");
        cv.put(COL_IMAGE, "67fbf663b0b6a7c227eb8b80a52fdec6_XL.png");
        db.insert(TABLE_NAME, null, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
} // ปิดคลาส DatabaseHelper
