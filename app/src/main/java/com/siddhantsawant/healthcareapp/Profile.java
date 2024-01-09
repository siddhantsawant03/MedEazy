package com.siddhantsawant.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    Button btnLogout;
    ImageButton back;
    TextView textViewPassword, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        displayUserData();

        btnLogout = findViewById(R.id.button7);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "You have been Successfully Logged Out", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(Profile.this, MainDisplayScreen.class);
                startActivity(i2);
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

    private void displayUserData() {
        try {
            SQLiteDatabase db = openOrCreateDatabase("HealthcareApp", MODE_PRIVATE, null);
            String selectQuery = "SELECT * FROM users WHERE email=?";


            Cursor cursor = db.rawQuery(selectQuery, new String[]{"sisa@gmail.com"});

            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));

                // Display the retrieved data in TextViews
                textViewPassword.setText(password);
                textViewEmail.setText(email);
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
