package com.siddhantsawant.healthcareapp;


import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.Date;

public class Reminder extends AppCompatActivity {
    private TimePicker timePicker;
    ImageButton back;
    BottomNavigationView nav;
    private Button btnTimer;
    private int jam, menit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        nav = findViewById(R.id.nav);
        navigation();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });


        timePicker = findViewById(R.id.timePicker);
        btnTimer = findViewById(R.id.btnTimer);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            jam = hourOfDay;
            menit = minute;
        }
    });

        btnTimer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Reminder.this, "Alarm Set For " + jam + " : " + menit, Toast.LENGTH_SHORT).show();
            setTimer();
            notification();
        }
    });
}

    private void notification() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Alarm Reminders";
            String description = "Hey, Wake Up!!";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel  = new NotificationChannel("Notify", name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setTimer() {
        AlarmManager alarmManager  = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent("your.package.name.ACTION_ALARM");

        Intent i = new Intent(Reminder.this, MyBroadcastReceiver.class);
        Date date = new Date();

        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY, jam);
        cal_alarm.set(Calendar.MINUTE, menit);
        cal_alarm.set(Calendar.SECOND, 0);

        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE, 1);
        }


        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 0, i, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(),pendingIntent);
        Log.d("Reminder", "Alarm set for " + cal_alarm.getTime());
    }


    public void navigation() {
        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent intent = null;

                if (item.getItemId() == R.id.menu_home) {
                    intent = new Intent(Reminder.this, MainDisplayScreen.class);
                } else if (item.getItemId() == R.id.menu_about) {
                    intent = new Intent(Reminder.this, About.class);
                } else if (item.getItemId() == R.id.menu_settings) {
                    intent = new Intent(Reminder.this, Profile.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

}