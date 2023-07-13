package com.example.ourrecipe;

public class Recipe {

    private String recipeName;
    private String ingredients;

    public Recipe(String recipeName, String ingredients, String howToCook, String cookingTime, String additionalInfo, String imageUrl) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.howToCook = howToCook;
        this.cookingTime = cookingTime;
        this.additionalInfo = additionalInfo;
        this.imageUrl = imageUrl;
    }

    private String howToCook;
    private String cookingTime;
    private String additionalInfo;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public Recipe(String recipeName, String image) {
        this.recipeName = recipeName;
        this.imageUrl = image;
    }

    public Recipe(String recipeName, String ingredients, String howToCook, String additionalInfo, String imageUrl) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.howToCook = howToCook;
        this.additionalInfo = additionalInfo;
        this.imageUrl = imageUrl;
    }


    public Recipe(String recipeName, String cookingTime, String ingredients, String image) {
        this.recipeName = recipeName;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.imageUrl = image;
    }
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getHowToCook() {
        return howToCook;
    }

    public void setHowToCook(String howToCook) {
        this.howToCook = howToCook;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }



    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }


}
