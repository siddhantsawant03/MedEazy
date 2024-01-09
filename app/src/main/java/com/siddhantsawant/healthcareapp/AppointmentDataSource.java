package com.siddhantsawant.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AppointmentDataSource {
    private SQLiteDatabase database;
    private AppointmentDBHelper dbHelper;

    public AppointmentDataSource(Context context) {
        dbHelper = new AppointmentDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createAppointment(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(AppointmentDBHelper.COLUMN_DOCTOR_NAME, appointment.getDoctorName());
        values.put(AppointmentDBHelper.COLUMN_DATE_TIME, appointment.getDateTime());
        return database.insert(AppointmentDBHelper.TABLE_APPOINTMENTS, null, values);
    }

    public Cursor getAllAppointments() {
        return database.query(AppointmentDBHelper.TABLE_APPOINTMENTS, null, null, null, null, null, null);
    }
}
