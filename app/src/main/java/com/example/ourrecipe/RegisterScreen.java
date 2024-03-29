package com.example.ourrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;

public class RegisterScreen extends AppCompatActivity {

    private String correctEmail;
    private String correctPassword;
    private String correctUserName;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    private DatabaseReference database;
    private FirebaseStorage firebaseStorage;
    public void NewUser(String userId, String name, String email) {
        ClassUser user = new ClassUser(name, email);

        database.child("users").child(userId).setValue(user);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        EditText editTextEmail = findViewById(R.id.editTextEmailReg);
        correctEmail = editTextEmail.getText().toString();
        EditText editTextPassword = findViewById(R.id.editTextPasswordReg);
        correctPassword = editTextPassword.getText().toString();
        EditText editTextUserName = findViewById(R.id.editTextUserNameReg);
        correctUserName = editTextUserName.getText().toString();
        Button btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progress_Bar_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                String email, password, userName;
                email = String.valueOf(editTextEmail.getText());
                password =String.valueOf(editTextPassword.getText());
                userName = String.valueOf(editTextUserName.getText());
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(RegisterScreen.this,"Enter email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterScreen.this,"Enter password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(userName))
                {
                    Toast.makeText(RegisterScreen.this,"Enter UserName", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    NewUser(mAuth.getUid(), userName,email);
                                    Toast.makeText(RegisterScreen.this, "Authentication created.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        TextView btnLoginNav = findViewById(R.id.login_nav);
        btnLoginNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}