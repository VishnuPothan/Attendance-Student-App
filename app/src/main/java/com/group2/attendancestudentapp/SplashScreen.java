package com.group2.attendancestudentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.attendancestudentapp.SharedPreference.SharedPreference;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {

    ImageView logoSplashScreen;
    TextView welcomeText;
    Animation zoomAnimation, fadeAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        logoSplashScreen = findViewById(R.id.logo);
        welcomeText = findViewById(R.id.welcome_text);

        zoomAnimation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom);
        logoSplashScreen.setAnimation(zoomAnimation);

        fadeAnimation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.fade);
        welcomeText.setAnimation(fadeAnimation);

        new Handler().postDelayed(() -> {
            if (SharedPreference.getUserVerified(getApplicationContext())){
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mainIntent);
            }else {
                Intent verifyIntent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(verifyIntent);
            }
            finish();
        }, 1500);
    }
}