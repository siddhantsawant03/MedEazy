package com.siddhantsawant.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Gethelp extends AppCompatActivity {
    ImageButton back;

    EditText edtName, edtPhone, edtSubject, edtIssue;
    Button btnSubmit;
    String Name, Phone, Subject, Issue;
    SQLiteDatabase db_gethelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gethelp);

        edtName = findViewById(R.id.et1);
        edtPhone = findViewById(R.id.et2);
        edtSubject = findViewById(R.id.et3);
        edtIssue = findViewById(R.id.et4);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });

        btnSubmit = findViewById(R.id.btn1);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = edtName.getText().toString();
                Phone = edtPhone.getText().toString();
                Subject = edtSubject.getText().toString();
                Issue = edtIssue.getText().toString();

                // Validate the inputs
                if (Name.trim().isEmpty()) {
                    // Name is empty
                    edtName.setError("Please enter your name");
                } else if (Phone.trim().isEmpty() || !isValidPhone(Phone)) {
                    // Phone is empty or not a valid phone number
                    edtPhone.setError("Please enter a valid phone number");
                } else if (Subject.trim().isEmpty()) {
                    // Subject is empty
                    edtSubject.setError("Please enter the subject");
                } else if (Issue.trim().isEmpty()) {
                    // Issue is empty
                    edtIssue.setError("Please enter the issue");
                } else {
                    // All inputs are valid, proceed with your logic
                    // Initialize the database helper
                    db objectdatabase = new db(getApplicationContext());


                    // Get a writable database
                    db_gethelp = objectdatabase.getWritableDatabase();

                    // Create the "gethelp" table if not exists
                    db_gethelp.execSQL("CREATE TABLE IF NOT EXISTS gethelp (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "Name TEXT," +
                            "Phone TEXT," +
                            "Subject TEXT," +
                            "Issue TEXT);");

                    // Insert data
                    ContentValues values = new ContentValues();
                    values.put("Name", Name);
                    values.put("Phone", Phone);
                    values.put("Subject", Subject);
                    values.put("Issue", Issue);

                    long ID = db_gethelp.insert("gethelp", null, values);

                    if (ID != -1) {
                        Toast.makeText(Gethelp.this, "Response Duly Noted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Gethelp.this, "Error saving data!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}