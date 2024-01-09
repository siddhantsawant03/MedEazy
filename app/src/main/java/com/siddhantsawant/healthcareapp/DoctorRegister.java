package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorRegister extends AppCompatActivity {
    public static final String  EM = "email";
    ImageButton back;

    public static final String SP   = "speciality";

//    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        EditText edt_Doc_username = findViewById(R.id.edt_doc_username);
        EditText edt_Doc_email = findViewById(R.id.edt_doc_email);
        EditText edt_Doc_speciality = findViewById(R.id.edt_doc_speciality);
        EditText edt_Doc_password = findViewById(R.id.edt_doc_password);
        Button btn_Doc_Register = findViewById(R.id.btn_doc_register);
        TextView tv_Doc_login = findViewById(R.id.docRegister);

//        nav = findViewById(R.id.nav);
//        navigation();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });


        tv_Doc_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorRegister.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_Doc_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_Doc_username.getText().toString();
                String email = edt_Doc_email.getText().toString();
                String password = edt_Doc_password.getText().toString();
                String speciality = edt_Doc_speciality.getText().toString();

                Database database = new Database(getApplicationContext(), "HealthcareApp", null, 1);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || speciality.length() == 0) {
                    Toast.makeText(DoctorRegister.this, "Please enter the details first!", Toast.LENGTH_SHORT).show();
                } else {
                    // Your registration logic here
                    database.doc_register(username, email, password,speciality);
                    Toast.makeText(DoctorRegister.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(DoctorRegister.this, DoctorLogin.class));
                    Intent i = new Intent(DoctorRegister.this,DoctorLogin.class);
                    i.putExtra(EM,email);
                    i.putExtra(SP,speciality);
                    startActivity(i);

                }
            }
        });


    }

//    public void navigation() {
//        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Intent intent = null;
//
//                if (item.getItemId() == R.id.menu_home) {
//                    intent = new Intent(DoctorRegister.this, MainDisplayScreen.class);
//                } else if (item.getItemId() == R.id.menu_about) {
//                    // You might want to change this to your profile activity
//                    intent = new Intent(DoctorRegister.this, About.class);
//                } else if (item.getItemId() == R.id.menu_settings) {
//                    intent = new Intent(DoctorRegister.this, Profile.class);
//                }
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}