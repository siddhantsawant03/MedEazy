package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorMain extends AppCompatActivity {
    BottomNavigationView nav;
    ImageButton back;
    ImageButton btnConsult,btnHistory,btnReminder,btnSettings,btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        nav = findViewById(R.id.nav);
        navigation();

        btnHelp = findViewById(R.id.btn5);
        btnReminder = findViewById(R.id.btn2);
        btnSettings = findViewById(R.id.btn4);
        btnHistory = findViewById(R.id.btn3);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorMain.this, PatientHistory.class);
                startActivity(i);
            }
        });

        btnConsult = findViewById(R.id.btn1);
        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7 = new Intent(DoctorMain.this,AppointmentScreen.class);
                startActivity(i7);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(DoctorMain.this, Profile.class);
                startActivity(i3);
            }
        });

        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(DoctorMain.this,Reminder.class);
                startActivity(i4);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(DoctorMain.this,Gethelp.class);
                startActivity(i5);
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });
//        return null;
    }


    public void navigation() {
        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent intent = null;

                if (item.getItemId() == R.id.menu_home) {
                    intent = new Intent(DoctorMain.this, MainDisplayScreen.class);
                } else if (item.getItemId() == R.id.menu_about) {
                    intent = new Intent(DoctorMain.this, About.class);
                } else if (item.getItemId() == R.id.menu_settings) {
                    intent = new Intent(DoctorMain.this, Profile.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}