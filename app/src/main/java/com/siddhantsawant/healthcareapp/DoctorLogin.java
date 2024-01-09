package com.siddhantsawant.healthcareapp;

import static com.siddhantsawant.healthcareapp.DoctorRegister.EM;
import static com.siddhantsawant.healthcareapp.DoctorRegister.SP;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorLogin extends AppCompatActivity{
//    BottomNavigationView nav;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

//        nav = findViewById(R.id.nav);
//        navigation();

        EditText edt_Doc_username = findViewById(R.id.edt_doc_username);
        EditText edt_Doc_password = findViewById(R.id.edt_doc_password);
//        EditText edt_Doc_email = findViewById(R.id.edt_doc_email);
//        EditText edt_Doc_speciality = findViewById(R.id.edt_speciality);
        Button btn_Doc_signin = findViewById(R.id.btn_doctor_login);
        TextView tv_Doc_Register = findViewById(R.id.textView_Register);

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });

        btn_Doc_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_Doc_username.getText().toString();
                String password = edt_Doc_password.getText().toString();
                Intent i2 = getIntent();
                String email = i2.getStringExtra(EM);
                String speciality = i2.getStringExtra(SP);
                // Ensure that edt_Doc_email and edt_Doc_speciality are not null
                if (email != null && speciality != null) {

                    // Use try-with-resources to close the database automatically
                    try (Database database = new Database(getApplicationContext(), "HealthcareApp", null, 1)) {
                        if (username.length() == 0 || password.length() == 0) {
                            Toast.makeText(DoctorLogin.this, "Please enter the details first!", Toast.LENGTH_SHORT).show();
                        } else {
//                            int val = database.login_doc(username, password, email, speciality);
                            if (database.login_doc(username, password) == 1) {
                                Toast.makeText(DoctorLogin.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                                // Save the details for later use
                                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.apply();
                                startActivity(new Intent(DoctorLogin.this, PatientActivity.class));
                            } else {
                                Toast.makeText(DoctorLogin.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(DoctorLogin.this, "Error accessing the database: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle the case where either edt_Doc_email or edt_Doc_speciality is null
                    Toast.makeText(DoctorLogin.this, "Error: Email or Speciality is null", Toast.LENGTH_SHORT).show();
                }
            }
        });



        tv_Doc_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorLogin.this, DoctorRegister.class);
                startActivity(intent);
            }
        });
//        return null;
    }

//    public void navigation() {
//        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Intent intent = null;
//
//                if (item.getItemId() == R.id.menu_home) {
//                    intent = new Intent(DoctorLogin.this, MainDisplayScreen.class);
//                } else if (item.getItemId() == R.id.menu_about) {
//                    intent = new Intent(DoctorLogin.this, About.class);
//                } else if (item.getItemId() == R.id.menu_settings) {
//                    intent = new Intent(DoctorLogin.this, Profile.class);
//                }
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}
