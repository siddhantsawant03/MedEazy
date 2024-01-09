package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class AppointmentScreen extends AppCompatActivity {

    ImageButton btnBack;
    EditText doctorNameEditText, dateTimeEditText;
    private AppointmentDataSource dataSource;
    Button scheduleButton,btnDisplay;
    BottomNavigationView nav;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_screen);
        btnBack = findViewById(R.id.back);

        nav = findViewById(R.id.nav);
        navigation();

        dataSource = new AppointmentDataSource(this);
        dataSource.open();

        doctorNameEditText = findViewById(R.id.editTextDoctorName);
        dateTimeEditText = findViewById(R.id.editTextDateTime);
        scheduleButton = findViewById(R.id.scheduleButton);
        btnDisplay = findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AppointmentDoctorDisplay.class);
                startActivity(intent);
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = doctorNameEditText.getText().toString();
                String dateTime = dateTimeEditText.getText().toString();

                // Validate the inputs
                if (doctorName.trim().isEmpty()) {
                    // Doctor name is empty
                    doctorNameEditText.setError("Please enter the doctor's name");
                } else if (dateTime.trim().isEmpty()) {
                    // Date and time is empty
                    dateTimeEditText.setError("Please enter the date and time");
                    // You may want to use a DateTimePicker for a more robust solution
                } else {
                    Appointment appointment = new Appointment(doctorName, dateTime);
                    long appointmentId = dataSource.createAppointment(appointment);

                    // Handle the appointment creation, you can show a confirmation message here.
                    Toast.makeText(getApplicationContext(),"Appointment Set",Toast.LENGTH_SHORT).show();

                    doctorNameEditText.setText("");
                    dateTimeEditText.setText("");
                }
            }
        });

        // Use your Database class to create SQLiteOpenHelper
//        AppointmentDBHelper dbHelper = new AppointmentDBHelper(this);
//        db = dbHelper.getWritableDatabase();



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), DoctorMain.class);
                startActivity(i2);
            }
        });
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    public void navigation() {
        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent intent = null;

                if (item.getItemId() == R.id.menu_home) {
                    Intent i2 = new Intent(AppointmentScreen.this, MainDisplayScreen.class);
                    startActivity(i2);
                } else if (item.getItemId() == R.id.menu_about) {
                    Intent i3 = new Intent(AppointmentScreen.this, About.class);
                    startActivity(i3);
                } else if (item.getItemId() == R.id.menu_settings) {
                    Intent i4 = new Intent(AppointmentScreen.this, Profile.class);
                    startActivity(i4);
                }
            }
        });
    }
}