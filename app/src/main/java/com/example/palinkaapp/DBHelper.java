package com.example.palinkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "palinkak.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "palinka";
    private static final String COL_ID = "ID";
    private static final String COL_FOZO = "fozo";
    private static final String COL_GYUMOLCS = "gyumolcs";
    private static final String COL_ALKOHOL = "alkohol";
    private TextView txtEredmeny;

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

    public boolean rogzites(String fozo, String gyumolcs, int alkoholTartalom)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOZO, fozo);
        values.put(COL_GYUMOLCS, gyumolcs);
        values.put(COL_ALKOHOL, alkoholTartalom);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor adatLekerdezes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME,new String[] {COL_ID,COL_FOZO,COL_GYUMOLCS,COL_ALKOHOL},
                null, null, null,null,null);
    }

    public Cursor kereses(String fozo, String gyumolcs)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME,new String[] {COL_ID,COL_FOZO,COL_GYUMOLCS,COL_ALKOHOL},
                COL_FOZO + " like '%" + fozo + "%' AND " + COL_GYUMOLCS + " like '%" + gyumolcs + "%'", null, null,null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
