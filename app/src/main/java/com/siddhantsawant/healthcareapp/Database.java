package com.siddhantsawant.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text,email text,password text)";
        db.execSQL(qry1);

        String qry2 = "create table doctor(username text,email text,password text, speciality text)";
        db.execSQL(qry2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase(); //to fetch the data
        db.insert("users",null,cv);
        db.close();
    }
    public int doc_register(String username, String email, String password, String speciality) {
        ContentValues cv1 = new ContentValues();
        cv1.put("username", username);
        cv1.put("email", email);
        cv1.put("password", password);
        cv1.put("speciality", speciality);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("doctor", null, cv1);
        db.close();

        // Check if the registration was successful
        return (result != -1) ? 1 : 0;
    }


    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("select *from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }

    public int login_doc(String username, String password) {
        int result_doc = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("select *from doctor where username=? and password=?",str);
        if(c.moveToFirst()){
            result_doc=1;
        }
        return result_doc;
    }


}