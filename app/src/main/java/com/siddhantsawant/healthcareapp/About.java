package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class About extends AppCompatActivity {

    BottomNavigationView nav;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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
    }

    public void navigation() {
        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Intent intent = null;

                if (item.getItemId() == R.id.menu_home) {
                    intent = new Intent(About.this, MainDisplayScreen.class);
                } else if (item.getItemId() == R.id.menu_about) {
                    intent = new Intent(About.this, About.class);
                } else if (item.getItemId() == R.id.menu_settings) {
                    intent = new Intent(About.this, SettingsActvitiy.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}