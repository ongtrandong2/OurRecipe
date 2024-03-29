package com.example.ourrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnprofile = findViewById(R.id.profile);
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,UserProfile.class);
                startActivity(intent);
            }
        });
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, OnboardingScreen.class);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Button btnSignOut = findViewById(R.id.btnSignOut);
        if(user==null)
        {
            Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
            startActivity(intent);
            finish();
        }
        else {
            textView =findViewById(R.id.textView);
            textView.setText(user.getEmail());
        }
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}