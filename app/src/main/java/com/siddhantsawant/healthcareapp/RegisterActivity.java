package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
//    BottomNavigationView nav;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        nav = findViewById(R.id.nav);
//        navigation();

        EditText edt_username = findViewById(R.id.edt_username);
        EditText edt_email = findViewById(R.id.edt_email);
        EditText edt_confirmpassword = findViewById(R.id.edt_confirm_password);
        EditText edt_password = findViewById(R.id.edt_password);
        Button btn_Register = findViewById(R.id.btn_register);
        TextView tv_login = findViewById(R.id.textView_Register);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainDisplayScreen.class);
                startActivity(i2);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                String con_password = edt_confirmpassword.getText().toString();
                Database database = new Database(getApplicationContext(),"HealthcareApp",null,1);
                if(username.length() ==0 || email.length()==0 || password.length()==0 || con_password.length()==0){
                    Toast.makeText(RegisterActivity.this,"Please enter the details first!",Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(con_password)==0){
                        //passwords match
                        database.register(username,email,password);
                        if(isValid(password)){
                            //insert data into db
                            Toast.makeText(RegisterActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this,"Password must contain at least 8 characters,a digit and a special symbol",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(RegisterActivity.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


//        return null;
    }
    public static boolean isValid(String passwordCheck){
        int f1=0,f2=0,f3=0;
        if(passwordCheck.length() < 8){
            return false;
        }else{
            for(int p=0;p<passwordCheck.length();p++){
                if(Character.isLetter(passwordCheck.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwordCheck.length();r++){
                if(Character.isDigit(passwordCheck.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<passwordCheck.length();s++){
                char c = passwordCheck.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }

//    public void navigation() {
//        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Intent intent = null;
//
//                if (item.getItemId() == R.id.menu_home) {
//                    intent = new Intent(RegisterActivity.this, MainDisplayScreen.class);
//                } else if (item.getItemId() == R.id.menu_about) {
//                    intent = new Intent(RegisterActivity.this, About.class);
//                } else if (item.getItemId() == R.id.menu_settings) {
//                    intent = new Intent(RegisterActivity.this, Profile.class);
//                }
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}