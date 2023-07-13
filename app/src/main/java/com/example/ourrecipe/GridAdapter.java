//package com.example.ourrecipe;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class GridAdapter extends BaseAdapter {
//    Context context;
//    int images[];
//    String names[];
//    LayoutInflater inflater;
//
//    public GridAdapter(Context applicationContext, int[] images, String[] names) {
//        this.context = applicationContext;
//        this.images = images;
//        this.names = names;
//        inflater = (LayoutInflater.from(applicationContext));
//    }
//
//    @Override
//    public int getCount() {
//        return images.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = inflater.inflate(R.layout.grid_item_user, null); // inflate the layout
//        ImageView image = (ImageView) view.findViewById(R.id.gridItemImage); // get the reference of ImageView
//        TextView name = (TextView) view.findViewById(R.id.gridItemName);
//        image.setImageResource(images[i]); // set logo images
//        name.setText(names[i]);
//        return view;
//    }
//}
package com.example.ourrecipe;

import android.app.Activity;
import android.content.Intent;
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

public class GridAdapter extends ArrayAdapter<Recipe> {

    Activity context;
    int IdLayout;
    ArrayList<Recipe> myListRecipe;

    public GridAdapter(Activity context1, int idLayout, ArrayList<Recipe> myListRecipe) {
        super(context1, idLayout, myListRecipe);
        this.context = context1;
        IdLayout = idLayout;
        this.myListRecipe = myListRecipe;
    }

    @Override
    public int getCount() {
        return myListRecipe.toArray().length;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater myInflater = context.getLayoutInflater();
        view  = myInflater.inflate(IdLayout, null);
        Recipe myRecipe = myListRecipe.get(i);
        view = myInflater.inflate(R.layout.grid_item_user, null); // inflate the layout
        ImageView image = (ImageView) view.findViewById(R.id.gridItemImage); // get the reference of ImageView
        TextView name = (TextView) view.findViewById(R.id.gridItemName);
        Picasso.get().load(myRecipe.getImageUrl()).into(image);// set logo images
        name.setText(myRecipe.getRecipeName());
        view.setOnClickListener(new View.OnClickListener() {
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
        return view;
    }
}
