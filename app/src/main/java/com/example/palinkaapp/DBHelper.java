package com.example.palinkaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "palinkak.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "palinka";
    private static final String COL_ID = "ID";
    private static final String COL_FOZO = "fozo";
    private static final String COL_GYUMOLCS = "gyumolcs";
    private static final String COL_ALKOHOL = "alkohol";

    //public DBHepler(Context context)
    //{
    //    super(context, DB_NAME, null,DB_VERSION);
    //}

    public DBHelper(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FOZO + " TEXT NOT NULL, " +
                COL_GYUMOLCS + " TEXT NOT NULL, " +
                COL_ALKOHOL + " INTEGER NOT NULL, " + "UNIQUE(" + COL_FOZO + "," + COL_GYUMOLCS+ ") );";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
