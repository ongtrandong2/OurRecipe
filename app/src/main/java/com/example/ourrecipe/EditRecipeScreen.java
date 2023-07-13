package com.example.ourrecipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class EditRecipeScreen extends AppCompatActivity {
    String recipeName = new String();
    private ImageView back_button;
    private ImageView recipe_avatar;
    private EditText recipe_name;
    private TextInputEditText edit_how_to_cook;
    private TextInputEditText edit_ingredient;
    private TextInputEditText edit_time_to_cook;
    private TextInputEditText edit_additional_info;

    private Button save_recipe;
    private Button delete_recipe;
    String RecipeName;
    String ingredients;
    String howToCook;
    String timeToCook;
    String imageUrl;
    String additionalInfo;
    Recipe recipe ;

    private ActivityResultLauncher<Intent> openStorageResultLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent getStorageImage = result.getData();
                        recipe_avatar.setImageURI(getStorageImage.getData());
                    }
                }
            });
    private void readData()
    {
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(recipe_avatar);
        }
        if (RecipeName != null) {
            recipe_name.setText(RecipeName);
        }
        if (ingredients != null) {
            edit_ingredient.setText(ingredients);

        }
        if (howToCook != null) {
            edit_how_to_cook.setText(howToCook);

        }
        if (timeToCook != null) {
            edit_time_to_cook.setText(timeToCook);

        }
        if (additionalInfo != null) {
            edit_additional_info.setText(additionalInfo);

        }


    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recipe_screen);


        back_button = findViewById(R.id.imgV_back_button_edit_recipe);
        recipe_avatar = findViewById(R.id.imgV_recipe_avatar);
        recipe_name = findViewById(R.id.et_recipe_name);
        edit_ingredient = findViewById(R.id.et_ingredient_edit_recipe);
        edit_how_to_cook = findViewById(R.id.et_how_to_cook);
        edit_time_to_cook = findViewById(R.id.et_time_to_cook_edit_recipe);
        edit_additional_info = findViewById(R.id.et_additional_info_edit_recipe);
        save_recipe = findViewById(R.id.btn_save_recipe_edit_recipe);

        RecipeName = getIntent().getStringExtra("recipeName");
        ingredients = getIntent().getStringExtra("ingredients");
        howToCook = getIntent().getStringExtra("howToCook");
        timeToCook = getIntent().getStringExtra("timeToCook");
        imageUrl = getIntent().getStringExtra("imageUrl");
        additionalInfo =getIntent().getStringExtra("additionalInfo");
        Log.d("AAA", imageUrl);
        Recipe recipe = new Recipe(RecipeName, ingredients, howToCook,timeToCook,additionalInfo,imageUrl);
        readData();


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        recipe_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openYourStorage = new Intent();
                openYourStorage.setAction(Intent.ACTION_GET_CONTENT);
                openYourStorage.setType("image/*");
                openStorageResultLaucher.launch(openYourStorage);
            }
        });

        save_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recipeName = recipe_name.getText().toString();
            }
        });
    }

}
