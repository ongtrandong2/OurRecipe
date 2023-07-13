package com.example.ourrecipe;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {
    GridView gridView;
    String names[] = {"aaaa", "bbbb", "cccc", "dddd", "eeee", "gggg"};
    FirebaseAuth mAuth;
    DatabaseReference ref;
    TextView username,tilte,num_recipe;
    ImageView edit;
    GridAdapter gridAdapter;
    ArrayList<Recipe> mylist;
    int count;
    int images[] = {R.drawable.image__1_, R.drawable.image__2_, R.drawable.image__3_, R.drawable.image__4_, R.drawable.image__4_, R.drawable.image__4_};

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        username = findViewById(R.id.name);
        tilte = findViewById(R.id.text_title);
        edit = findViewById(R.id.btn_edit);
        num_recipe = findViewById(R.id.numRecipe);
        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth

        mylist = new ArrayList<Recipe>();
        readData();
        readDataFromDatabase();
        Log.d("AAAAA", Integer.toString(count));
        num_recipe.setText(count+"\nRecipes");
        gridView = findViewById(R.id.gridView);
        gridAdapter = new GridAdapter(UserProfile.this,R.layout.grid_item_user,mylist);
        gridView.setAdapter(gridAdapter);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this,EditProfile.class);
                startActivity(intent);
                finish();
            }
        });

        drawerLayout = findViewById(R.id.drawerLayoutUserProfile);
        navigationView = findViewById(R.id.navigationViewDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent();
                switch (item.getItemId()) {
                    case R.id.navUserProfile:
                        Log.i("MENU_DRAWER_TAG", "User Profile is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navBrowseRecipe:
                        i.setClass(UserProfile.this, BrowseRecipeScreen.class);
                        Log.i("MENU_DRAWER_TAG", "Browse Recipe is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(i);
                        break;
                    case R.id.navLogout:
                        FirebaseAuth.getInstance().signOut();
                        i.setClass(UserProfile.this, LoginScreen.class);
                        Log.i("MENU_DRAWER_TAG", "Log-out is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });
    }


    private void readDataFromDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid()).child("Recipes");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    String RecipeName = recipeSnapshot.child("recipeName").getValue(String.class);
                    String image = recipeSnapshot.child("imageUrl").getValue(String.class);
                    if (RecipeName != null) {
                        mylist.add(new Recipe(RecipeName, image));

                    }
                }
                gridAdapter.notifyDataSetChanged();
                count = mylist.size();
                num_recipe.setText(count+"\nRecipes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void readData() {
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String bio = dataSnapshot.child("bio").getValue(String.class);
                    if (name != null) {
                        username.setText(name);
                        tilte.setText(bio);
                    }
                    Toast.makeText(UserProfile.this, "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfile.this, "Does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserProfile.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
