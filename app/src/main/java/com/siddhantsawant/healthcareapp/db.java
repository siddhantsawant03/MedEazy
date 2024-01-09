package com.siddhantsawant.healthcareapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class db extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "healthDatabase";


    public db(Context context) {

        super((Context) context, "HealthcareApp", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_DOCTOR_TABLE = "CREATE TABLE doctordetails(Name  TEXT NOT NULL ,Speciality TEXT NOT NULL , Age TEXT NOT NULL , Phone INTEGER PRIMARY KEY " + ")";
//        db.execSQL(CREATE_DOCTOR_TABLE);

//        String CREATE_PATIENT_TABLE = "CREATE TABLE patientdetails(Name  TEXT NOT NULL , Age TEXT NOT NULL , Phone INTEGER PRIMARY KEY AUTOINCREMENT" + ")";
//        db.execSQL(CREATE_PATIENT_TABLE);

//        String CREATE_PATIENT_HISTORY = "CREATE TABLE historydetails(Name  TEXT NOT NULL ,Diseases TEXT NOT NULL,DiseaseAge TEXT NOT NULL , Phone INTEGER PRIMARY KEY " + ")";
//        db.execSQL(CREATE_PATIENT_HISTORY);

        String GET_HELP = "CREATE TABLE gethelp(Name  TEXT NOT NULL ,Phone INTEGER PRIMARY KEY ,Subject TEXT NOT NULL , Issue INTEGER " + ")";
        db.execSQL(GET_HELP);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}