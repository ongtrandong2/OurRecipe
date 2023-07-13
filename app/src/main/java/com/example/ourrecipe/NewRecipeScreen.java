package com.example.ourrecipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class NewRecipeScreen extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextRecipeName;
    private EditText editTextIngredients;
    private EditText editTextHowToCook;
    private EditText editTextAdditionalInfo;
    private ImageView imageViewRecipe;
    private ImageView imgViewBackButton;

    private StorageReference storageReference;
    private DatabaseReference  recipeDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe_screen);

        editTextRecipeName = findViewById(R.id.editTextRecipeName);
        editTextIngredients = findViewById(R.id.viewIngredients);
        editTextHowToCook = findViewById(R.id.viewHowToCook);
        editTextAdditionalInfo = findViewById(R.id.viewAdditionalInfo);
        imageViewRecipe = findViewById(R.id.imgViewRecipe);
        imgViewBackButton = findViewById(R.id.imageViewBackBtn);

        storageReference = FirebaseStorage.getInstance().getReference();
        recipeDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Button buttonPost = findViewById(R.id.btnPostToFeed);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRecipe();
            }
        });

        imageViewRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });

        imgViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { backToBrowseRecipe(); }
        });
    }

    private void backToBrowseRecipe() {
        Animation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        imgViewBackButton.startAnimation(scaleAnimation);
        Intent intent = new Intent(NewRecipeScreen.this, BrowseRecipeScreen.class);
        startActivity(intent);
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            imageViewRecipe.setImageURI(imageUri);
        }
    }

    private void postRecipe() {
        String recipeName = editTextRecipeName.getText().toString().trim();
        String ingredients = editTextIngredients.getText().toString().trim();
        String howToCook = editTextHowToCook.getText().toString().trim();
        String additionalInfo = editTextAdditionalInfo.getText().toString().trim();

        if (TextUtils.isEmpty(recipeName) || TextUtils.isEmpty(ingredients) ||
                TextUtils.isEmpty(howToCook) || TextUtils.isEmpty(additionalInfo)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageViewRecipe.getDrawable() == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }
        StorageReference imageRef = storageReference.child("images/" + UUID.randomUUID().toString());
        imageViewRecipe.setDrawingCacheEnabled(true);
        imageViewRecipe.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageViewRecipe.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageData = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(imageData);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        Recipe recipe = new Recipe(recipeName, ingredients, howToCook, additionalInfo, imageUrl);
                        String uploadId = recipeDatabase.push().getKey();
                        recipeDatabase.child("users").child(mAuth.getUid()).child("Recipes").child(uploadId).setValue(recipe)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(NewRecipeScreen.this, "Recipe posted successfully", Toast.LENGTH_SHORT).show();
                                        clearFields();
                                        Intent intent = new Intent(NewRecipeScreen.this,BrowseRecipeScreen.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(NewRecipeScreen.this, "Failed to post recipe: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewRecipeScreen.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearFields() {
        editTextRecipeName.setText("");
        editTextIngredients.setText("");
        editTextHowToCook.setText("");
        editTextAdditionalInfo.setText("");
        imageViewRecipe.setImageResource(R.drawable.placeholder_image);
    }
}
