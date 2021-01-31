package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Student.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table StudentInfo (id TEXT primary key, name TEXT, address TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exist StudentInfo");
    }

    public Boolean InsertStudent (String id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("address", address);
        long result=db.insert("StudentInfo", null, contentValues);
        if (result== -1){
            return false;
        }else{
            return true;
        }

    }
    public Boolean UpdateStudent (String id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        Cursor cursor = db.rawQuery("select * from StudentInfo where id =?",new String[]{id});
        if (cursor.getCount()>0) {
            long result = db.update("StudentInfo", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {

            return false;
        }}

    public Boolean DeleteStudent (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from StudentInfo where id =?", new String[]{id});
        if (cursor.getCount()>0) {
            long result = db.delete("StudentInfo", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {

            return false;
        }}

    public Cursor getdata () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from StudentInfo",null);
        return cursor;

    }
}
