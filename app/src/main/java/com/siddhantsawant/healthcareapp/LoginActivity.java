package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LoginActivity extends AppCompatActivity {

//    BottomNavigationView nav;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        nav = findViewById(R.id.nav);
//        navigation();

        EditText edt_username = findViewById(R.id.edt_username);
        EditText edt_password = findViewById(R.id.edt_password);
        Button btn_signin = findViewById(R.id.btn_signin);
        TextView tv_Register = findViewById(R.id.textView_Register);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                Database database = new Database(getApplicationContext(),"HealthcareApp",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(LoginActivity.this,"Please enter the details first!",Toast.LENGTH_SHORT).show();
                }else{
                    if(database.login(username,password)==1){
                        Toast.makeText(LoginActivity.this,"Successfully Logged In!",Toast.LENGTH_SHORT).show();
                        //to save the details so that we can display it later
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        //to save data with key and value
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, PatientActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Username and Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
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
//                    intent = new Intent(LoginActivity.this, MainDisplayScreen.class);
//                } else if (item.getItemId() == R.id.menu_about) {
//                    intent = new Intent(LoginActivity.this, About.class);
//                } else if (item.getItemId() == R.id.menu_settings) {
//                    intent = new Intent(LoginActivity.this, Profile.class);
//                }
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}