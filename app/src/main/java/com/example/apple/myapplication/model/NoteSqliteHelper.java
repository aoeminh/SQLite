package com.example.apple.myapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteSqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mynotes.db";
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE = "note";
    public static  final String COLUMN_DATE = "date";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE = "Create table " + TABLE_NAME +" ("
                                                               + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                                + COLUMN_NOTE + " text not null,"
                                                                + COLUMN_DATE +" text);";
    //create database
    public NoteSqliteHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE EXIST"  + TABLE_NAME);
        onCreate(db);
    }

}
