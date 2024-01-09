package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private static final String TABLE_NAME = "users";
    BottomNavigationView nav;
    ImageButton back;

    EditText et1, et2, et3;
    Button b1, b2, b3;

    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.nav);
        navigation();

        et1 = findViewById(R.id.name);
        et2 = findViewById(R.id.gmail);
        et3 = findViewById(R.id.pass);

        b1 = findViewById(R.id.update);
        b2 = findViewById(R.id.display);
        b3 = findViewById(R.id.save);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String username = et1.getText().toString();
                    String email = et2.getText().toString();
                    String password = et3.getText().toString();

                    // Validate the inputs
                    if (username.trim().isEmpty()) {
                        // Username is empty
                        et1.setError("Please enter a username");
                    } else if (email.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        // Email is empty or not a valid email address
                        et2.setError("Please enter a valid email address");
                    } else if (password.trim().isEmpty() || password.length() < 6) {
                        // Password is empty or less than 6 characters
                        et3.setError("Password must be at least 6 characters long");
                    } else {
                        SQLiteDatabase db = openOrCreateDatabase("HealthcareApp", Context.MODE_PRIVATE, null);
                        db.execSQL("CREATE TABLE IF NOT EXISTS users(username TEXT NOT NULL,email VARCHAR PRIMARY KEY,password VARCHAR NOT NULL)");
                        String sql = "insert into users(username,email,password)values(?,?,?)";
                        SQLiteStatement statement = db.compileStatement(sql);
                        statement.bindString(1, username);
                        statement.bindString(2, email);
                        statement.bindString(3, password);
                        statement.execute();

                        Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
                        et1.setText("");
                        et2.setText("");
                        et3.setText("");
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String username = et1.getText().toString();
                    String email = et2.getText().toString();
                    String password = et3.getText().toString();

                    if (username.trim().isEmpty()) {
                        // Username is empty
                        et1.setError("Please enter a username");
                    } else if (email.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        // Email is empty or not a valid email address
                        et2.setError("Please enter a valid email address");
                    } else if (password.trim().isEmpty() || password.length() < 6) {
                        // Password is empty or less than 6 characters
                        et3.setError("Password must be at least 6 characters long");
                    } else {
                        SQLiteDatabase db = openOrCreateDatabase("HealthcareApp", Context.MODE_PRIVATE, null);
                        // Corrected the table name to "users"
                        String sql = "update users set username = ?, email=?, password=?";
                        SQLiteStatement statement = db.compileStatement(sql);
                        statement.bindString(1, username);
                        statement.bindString(2, email);
                        statement.bindString(3, password);
                        statement.execute();

                        Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        et1.setText("");
                        et2.setText("");
                        et3.setText("");
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = et1.getText().toString();
                String email = et2.getText().toString();
                String password = et3.getText().toString();
                try {
//                    SQLiteDatabase db = openOrCreateDatabase("HealthcareApp", MODE_PRIVATE, null);
//                    String selectQuery = "SELECT * FROM users where email=?";

//                    if (username.trim().isEmpty()) {
//                        // Username is empty
//                        et1.setError("Please enter a username");
//                    } else if (email.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                        // Email is empty or not a valid email address
//                        et2.setError("Please enter a valid email address");
//                    } else if (password.trim().isEmpty() || password.length() < 6) {
//                        // Password is empty or less than 6 characters
//                        et3.setError("Password must be at least 6 characters long");
//                    } else {
                        SQLiteDatabase db = openOrCreateDatabase("HealthcareApp", MODE_PRIVATE, null);
                        String selectQuery = "SELECT * FROM " + TABLE_NAME + " where email=?";
                        Cursor cursor = db.rawQuery(selectQuery, new String[]{email});
                        if (cursor.getCount() == 0) {
                            Toast.makeText(MainActivity.this, "Error, no records found", Toast.LENGTH_SHORT).show();
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            if (cursor.moveToFirst()) {
                                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("username"));
                                @SuppressLint("Range") String gmail = cursor.getString(cursor.getColumnIndex("email"));
                                @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex("password"));

                                et1.setText(name);
                                et2.setText(gmail);
                                et3.setText(pass);
                            }

                        }
//                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error retrieving records", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void navigation() {
        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent intent = null;

                if (item.getItemId() == R.id.menu_home) {
                    intent = new Intent(MainActivity.this, MainDisplayScreen.class);
                } else if (item.getItemId() == R.id.menu_about) {
                    intent = new Intent(MainActivity.this, About.class);
                } else if (item.getItemId() == R.id.menu_settings) {
                    intent = new Intent(MainActivity.this, Profile.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}