package com.example.ourrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ourrecipe.CustomFoodCardAdapter;
import com.example.ourrecipe.LoginScreen;
import com.example.ourrecipe.NewRecipeScreen;
import com.example.ourrecipe.R;
import com.example.ourrecipe.Recipe;
import com.example.ourrecipe.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BrowseRecipeScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private ArrayList<Recipe> myList;
    private CustomFoodCardAdapter customFoodCardAdapter;
    private ListView listView;
    private TextView navProfile;
    private ImageView btnAddNew;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private void readData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid()).child("Recipes");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myList.clear();
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    String recipeName = recipeSnapshot.child("recipeName").getValue(String.class);
                    String cookingTime = recipeSnapshot.child("howToCook").getValue(String.class);
                    String ingredients = recipeSnapshot.child("Ingredients").getValue(String.class);
                    String image = recipeSnapshot.child("imageUrl").getValue(String.class);
                    if (recipeName != null) {
                        myList.add(new Recipe(recipeName, cookingTime, ingredients, image));
                    }
                }
                customFoodCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_recipe_screen);

        mAuth = FirebaseAuth.getInstance();
        listView = findViewById(R.id.listViewFoodCard);
        myList = new ArrayList<>();
        customFoodCardAdapter = new CustomFoodCardAdapter(BrowseRecipeScreen.this, R.layout.activity_browse_recipe_list_view, myList);
        listView.setAdapter(customFoodCardAdapter);



        btnAddNew = findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrowseRecipeScreen.this, NewRecipeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        drawerLayout = findViewById(R.id.drawerLayoutBrowseRecipe);
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
                        i.setClass(BrowseRecipeScreen.this, UserProfile.class);
                        Log.i("MENU_DRAWER_TAG", "User Profile is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(i);
                        break;
                    case R.id.navBrowseRecipe:
                        Log.i("MENU_DRAWER_TAG", "Browse Recipe is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navLogout:
                        FirebaseAuth.getInstance().signOut();
                        i.setClass(BrowseRecipeScreen.this, LoginScreen.class);
                        Log.i("MENU_DRAWER_TAG", "Log-out is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("users");
        readData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
