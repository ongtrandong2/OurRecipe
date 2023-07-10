package com.example.ourrecipe;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ourrecipe.Recipe;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
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
        txtViewCardName.setText(myRecipe.getCardName());

        TextView txtViewCookingTime = convertView.findViewById(R.id.txtViewCookingTime);
        txtViewCookingTime.setText("approximately " + myRecipe.getCookingTime() + " mins");

        TextView txtViewNumberOfIngredient = convertView.findViewById(R.id.txtViewNumberOfIngredient);
        txtViewNumberOfIngredient.setText(myRecipe.getNumberOfIngredient() + " Ingredients");

        ImageView imgRecipe = convertView.findViewById(R.id.imageViewCard);
        imgRecipe.setImageResource(myRecipe.getImage());
        return convertView;
    }
}
