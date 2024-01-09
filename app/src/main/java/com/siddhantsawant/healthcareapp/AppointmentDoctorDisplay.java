package com.siddhantsawant.healthcareapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentDoctorDisplay extends AppCompatActivity {

    ImageButton backBtn;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_doctor_display);

        SQLiteOpenHelper dbHelper = new db(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        tableLayout = findViewById(R.id.tableLayout);

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), AppointmentScreen.class);
                startActivity(i2);
            }
        });

        addRowToTable("Name", "Email", "Speciality"); // Add column headings

        String selectQuery = "SELECT * FROM doctor";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            showMessage("Error", "No records found:");
            return;
        }

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            String speciality = cursor.getString(2);

            addRowToTable(name, email, speciality);
        }

        cursor.close(); // Close the cursor after using it
    }

    private void addRowToTable(String name, String email, String speciality) {
        TableRow row = new TableRow(this);
        row.setBackgroundColor(getResources().getColor(android.R.color.white)); // Set background color to white

        // Add margins to the row
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 5, 5, 5);
        row.setLayoutParams(layoutParams);

        TextView nameTextView = createTextView(name, 16, Gravity.START, android.R.color.black, false); // Set font color to black without bold
        nameTextView.setBackgroundResource(R.drawable.cell_border); // Set cell border
        row.addView(nameTextView);

        TextView emailTextView = createTextView(email, 16, Gravity.START, android.R.color.black, false); // Set font color to black without bold
        emailTextView.setBackgroundResource(R.drawable.cell_border); // Set cell border
        row.addView(emailTextView);

        TextView specialityTextView = createTextView(speciality, 16, Gravity.START, android.R.color.black, false); // Set font color to black without bold
        specialityTextView.setBackgroundResource(R.drawable.cell_border); // Set cell border
        row.addView(specialityTextView);

        tableLayout.addView(row);

        // Set bold for the first row (column headings)
        if (tableLayout.getChildCount() == 1) { // Check if it's the first row
            for (int i = 0; i < row.getChildCount(); i++) {
                TextView textView = (TextView) row.getChildAt(i);
                textView.setTypeface(null, Typeface.BOLD);
            }
        }
    }



    private TextView createBoldTextView(String text, float textSize, int gravity, int fontColor) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setGravity(gravity);
        textView.setTextColor(getResources().getColor(fontColor)); // Set font color
        textView.setTypeface(null, Typeface.BOLD); // Make the text bold
        return textView;
    }





    private TextView createTextView(String text, float textSize, int gravity, int fontColor, boolean isBold) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setGravity(gravity);
        textView.setTextColor(getResources().getColor(fontColor)); // Set font color
        if (isBold) {
            textView.setTypeface(null, Typeface.BOLD); // Make the text bold
        }
        return textView;
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentDoctorDisplay.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}