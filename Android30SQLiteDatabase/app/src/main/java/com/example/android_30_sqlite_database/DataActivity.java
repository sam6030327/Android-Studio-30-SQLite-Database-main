package com.example.android_30_sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataActivity extends SQLiteOpenHelper {

    public static final String Data = "data.db";
    public static final String ScpTable = "SCP_table";
    public static final String ID1 = "ID";
    public static final String ID2 = "NAME";
    public static final String ID3 = "LEVEL";
    public static final String ID4 = "SITE";

    public DataActivity(@Nullable Context context) {
        super(context, Data, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ScpTable + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LEVEL TEXT, SITE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ScpTable);
        onCreate(db);
    }

    public boolean insertData(String name, String level, String site){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID2, name);
        contentValues.put(ID3, level);
        contentValues.put(ID4, site);
        long result = db.insert(ScpTable, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + ScpTable, null);
        return res;
    }

    public boolean updateData(String id, String name, String level, String site){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID1, id);
        contentValues.put(ID2, name);
        contentValues.put(ID3, level);
        contentValues.put(ID4, site);
        db.update(ScpTable, contentValues, "ID = ?", new String[]{ id });
        return  true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ScpTable, "ID = ?", new String[]{ id });
    }
}