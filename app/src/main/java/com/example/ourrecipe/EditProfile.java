package com.example.ourrecipe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference ref;
    EditText name, bio,email,phone;
    Button onSave;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = findViewById(R.id.et_name_edit_profile);
        bio = findViewById(R.id.et_bio_edit_profile);
        email = findViewById(R.id.et_email_edit_profile);
        phone = findViewById(R.id.et_phone_edit_profile);
        onSave = findViewById(R.id.btn_save_edit_profile);
        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth
        database = FirebaseDatabase.getInstance().getReference();
        readData();
        onSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = String.valueOf(name.getText());
                String UserBio = String.valueOf(bio.getText());
                String UserEmail = String.valueOf(email.getText());
                String UserPhone = String.valueOf(phone.getText());
                ClassUser user = new ClassUser(Username,UserBio,UserEmail,UserPhone);
                SetUser(mAuth.getUid(),user);
                Intent intent = new Intent(EditProfile.this,UserProfile.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void readData() {
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String PName = dataSnapshot.child("name").getValue(String.class);
                    String PBio = dataSnapshot.child("bio").getValue(String.class);
                    String PEmail = dataSnapshot.child("email").getValue(String.class);
                    String PPhone = dataSnapshot.child("phone").getValue(String.class);
                    if (name != null) {
                        name.setText(PName);
                        bio.setText(PBio);
                        email.setText(PEmail);
                        phone.setText(PPhone);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void SetUser(String userId,ClassUser user)
    {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(userId,user);
        database.child("users").updateChildren(childUpdates);
        Toast.makeText(EditProfile.this, "Successful", Toast.LENGTH_SHORT).show();
    }


}
