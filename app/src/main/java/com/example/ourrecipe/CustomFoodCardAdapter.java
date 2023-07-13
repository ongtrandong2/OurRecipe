package com.example.ourrecipe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomFoodCardAdapter extends ArrayAdapter<Recipe> {

    Activity context;
    int IdLayout;
    ArrayList<Recipe> myListRecipe;

    public CustomFoodCardAdapter(Activity context1, int idLayout, ArrayList<Recipe> myListRecipe) {
        super(context1, idLayout, myListRecipe);
        this.context = context1;
        IdLayout = idLayout;
        this.myListRecipe = myListRecipe;
    }

    @Override
    public int getCount() {
        return myListRecipe.toArray().length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = context.getLayoutInflater();
        convertView  = myInflater.inflate(IdLayout, null);
        Recipe myRecipe = myListRecipe.get(position);

        TextView txtViewCardName = convertView.findViewById(R.id.txtViewCardRecipeName);
        txtViewCardName.setText(myRecipe.getRecipeName());

        TextView txtViewCookingTime = convertView.findViewById(R.id.txtViewCookingTime);
        txtViewCookingTime.setText("approximately " + myRecipe.getHowToCook() + " mins");

        TextView txtViewNumberOfIngredient = convertView.findViewById(R.id.txtViewNumberOfIngredient);
        txtViewNumberOfIngredient.setText("");

        ImageView imgRecipe = convertView.findViewById(R.id.imageViewCard);
        Picasso.get()
                .load(myRecipe.getImageUrl())
                .resize(332, 204)
                .into(imgRecipe);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditRecipeScreen.class);
                intent.putExtra("recipeName", myRecipe.getRecipeName());
                intent.putExtra("ingredients", myRecipe.getIngredients());
                intent.putExtra("howToCook", myRecipe.getHowToCook());
                intent.putExtra("timeToCook", myRecipe.getCookingTime());
                intent.putExtra("imageUrl",myRecipe.getImageUrl());
                intent.putExtra("imageUrl",myRecipe.getImageUrl());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
