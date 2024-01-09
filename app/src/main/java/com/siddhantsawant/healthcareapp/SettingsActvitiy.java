package com.siddhantsawant.healthcareapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActvitiy extends AppCompatActivity {

    private Switch switchNotification;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activitiy);

        switchNotification = findViewById(R.id.switchNotification);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        boolean notificationEnabled = switchNotification.isChecked();

        String message = "Settings saved. Notifications: " + (notificationEnabled ? "Enabled" : "Disabled");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
