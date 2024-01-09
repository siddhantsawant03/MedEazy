package com.siddhantsawant.healthcareapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppointmentDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "healthDatabase";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String COLUMN_DATE_TIME = "date_time";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_APPOINTMENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DOCTOR_NAME + " TEXT, " +
                    COLUMN_DATE_TIME + " TEXT);";

    public AppointmentDBHelper(Context context) {
        super((Context) context, "HealthcareApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        onCreate(db);
    }
}