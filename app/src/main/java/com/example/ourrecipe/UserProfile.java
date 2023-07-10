package com.example.ourrecipe;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class UserProfile extends AppCompatActivity {
    GridView gridView;
    String names[] = {"aaaa","bbbb","cccc","dddd","eeee","gggg"};
    FirebaseFirestore firestore;
    int images[] ={R.drawable.image__1_, R.drawable.image__2_, R.drawable.image__3_, R.drawable.image__4_, R.drawable.image__4_, R.drawable.image__4_};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firestore = FirebaseFirestore.getInstance();
        Map<String,Object> user = new HashMap<>();
        user.put()

        gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), images,names);
        gridView.setAdapter(gridAdapter);
    }

}