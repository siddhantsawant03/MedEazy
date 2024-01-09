package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PatientHistory extends AppCompatActivity {

    Button btnSearch;
    private TextView illnessInput;
    private TextView diabetesInput;
    private TextView allergiesInput;
    private Button saveButton;
    private Button downloadButton;
    private ImageButton back;
    private BottomNavigationView nav;

    private RadioGroup illnessRadioGroup;
    private RadioGroup diabetesRadioGroup;
    private RadioGroup allergiesRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        nav = findViewById(R.id.nav);
        navigation();

        illnessInput = findViewById(R.id.illnessInput);
        diabetesInput = findViewById(R.id.diabetesInput);
        allergiesInput = findViewById(R.id.allergiesInput);
        saveButton = findViewById(R.id.saveButton);
        downloadButton = findViewById(R.id.downloadButton);
        back = findViewById(R.id.back);

        illnessRadioGroup = findViewById(R.id.illnessRadioGroup);
        diabetesRadioGroup = findViewById(R.id.diabetesRadioGroup);
        allergiesRadioGroup = findViewById(R.id.allergiesRadioGroup);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMedicalHistory();
                Toast.makeText(PatientHistory.this, "Medical history saved!", Toast.LENGTH_SHORT).show();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = getFilePath();
                openFile(filePath);
            }
        });
    }

    private void navigation() {
        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent intent = null;

                if (item.getItemId() == R.id.menu_home) {
                    intent = new Intent(PatientHistory.this, MainDisplayScreen.class);
                } else if (item.getItemId() == R.id.menu_about) {
                    intent = new Intent(PatientHistory.this, About.class);
                } else if (item.getItemId() == R.id.menu_settings) {
                    intent = new Intent(PatientHistory.this, SettingsActvitiy.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    private void saveMedicalHistory() {
        String medicalHistory = "Illness: " + getRadioButtonValue(illnessRadioGroup) + " "
                + illnessInput.getText().toString() + "\n"
                + "Diabetes: " + getRadioButtonValue(diabetesRadioGroup) + " "
                + diabetesInput.getText().toString() + "\n"
                + "Allergies: " + getRadioButtonValue(allergiesRadioGroup) + " "
                + allergiesInput.getText().toString();

        saveToFile(medicalHistory);
    }

    private String getRadioButtonValue(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        return radioButton != null ? radioButton.getText().toString() : "";
    }

    private void saveToFile(String data) {
        try {
            File directory = new File(Environment.getExternalStorageDirectory() + "/MedicalHistory");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, "medical_history.txt");
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(data);
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath() {
        String filePath = Environment.getExternalStorageDirectory() + "/MedicalHistory/medical_history.txt";
        Log.d("FilePath", "File path: " + filePath);
        return filePath;
    }


    private void openFile(String filePath) {
        File file = new File(filePath);
        Uri fileUri = null;
        try {
            fileUri = FileProvider.getUriForFile(
                    PatientHistory.this,
                    "com.siddhantsawant.healthcareapp.fileprovider",
                    file
            );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OpenFile", "Error opening file: " + e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "text/plain");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(intent);
    }
}