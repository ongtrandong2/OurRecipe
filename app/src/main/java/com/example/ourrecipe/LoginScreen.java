package com.example.ourrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    TextView email, password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginScreen.this,BrowseRecipeScreen.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login_screen);



        TextView textViewEmail = findViewById(R.id.txtViewEmailLog);
        textViewEmail.setText((CharSequence) email);

        EditText editTextEmail = findViewById(R.id.editTextEmailLog);

        EditText editTextPassword = findViewById(R.id.editTextPasswordLog);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.equals(editTextPassword.getText().toString()) && email.equals(editTextEmail.getText().toString())) {
                    Intent intent = new Intent(LoginScreen.this, BrowseRecipeScreen.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Password or Email", Toast.LENGTH_SHORT);
                }
            }
        });
        TextView btnRegisterNav = findViewById(R.id.register_nav);
        btnRegisterNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,RegisterScreen.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = findViewById(R.id.progress_Bar_login);
                progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginScreen.this,"enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginScreen.this,"password email",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this,BrowseRecipeScreen.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginScreen.this, "Auth failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}