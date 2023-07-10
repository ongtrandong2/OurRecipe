package com.example.ourrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class OnboardingScreen extends AppCompatActivity {

    private static final long DELAY_TIME = 2000;
    private Handler handler = new Handler();
    private Runnable  delayedNavigation = new Runnable() {
        @Override
        public void run() {
            navigateToLoginScreen();
        }
    };
    private void navigateToLoginScreen() {
        Intent i = new Intent();
        i.setClass(OnboardingScreen.this, LoginScreen.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        // Start the delay and navigation after the delay time
        handler.postDelayed(delayedNavigation, DELAY_TIME);
    }
}