package com.example.ourrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BrowseRecipeScreen extends AppCompatActivity {

    String cardNameList[] = {"Cooked Coconut Mussels", "Banana and Mandarin Buns", "Fried Salty & Sour Snapper", "Tenderized Hazelnut Pheasant"};
    String cardCookingTime[] = {"5", "45", "45", "45"};
    String cardIngredient[] = {"4", "4", "4", "4"};
    int cardImages[] = {R.drawable.img_example_recipe, R.drawable.img_example_recipe, R.drawable.img_example_recipe, R.drawable.img_example_recipe};

    ArrayList<Recipe> myList;
    CustomFoodCardAdapter customFoodCardAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_recipe_screen);

        listView = (ListView) findViewById(R.id.listViewFoodCard);
        myList = new ArrayList<>();
        for (int i = 0; i < cardNameList.length; i++) {
            myList.add(new Recipe(cardNameList[i], cardCookingTime[i], cardIngredient[i], cardImages[i]));
        }
        customFoodCardAdapter = new CustomFoodCardAdapter(BrowseRecipeScreen.this, R.layout.activity_browse_recipe_list_view, myList);
        listView.setAdapter(customFoodCardAdapter);
    }
}