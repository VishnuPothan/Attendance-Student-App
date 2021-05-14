package com.group2.attendancestudentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group2.attendancestudentapp.SharedPreference.SharedPreference;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView userNameText, roleText;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Bottom Navigation SetUp
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            finish();
            return false;
        });

        // initialize
        userNameText = findViewById(R.id.userNameText);
        roleText = findViewById(R.id.roleText);

        userNameText.setText(SharedPreference.getUserName(getApplicationContext()));
        roleText.setText(SharedPreference.getUserID(getApplicationContext()));
    }
}