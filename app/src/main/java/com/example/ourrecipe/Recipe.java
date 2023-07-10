package com.example.ourrecipe;

public class Recipe {
    private String cardName;
    private String cookingTime;
    private String numberOfIngredient;
    private int image;

    public Recipe(String cardName, String cookingTime, String numberOfIngredient, int image) {
        this.cardName = cardName;
        this.cookingTime = cookingTime;
        this.numberOfIngredient = numberOfIngredient;
        this.image = image;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getNumberOfIngredient() {
        return numberOfIngredient;
    }

    public void setNumberOfIngredient(String numberOfIngredient) {
        this.numberOfIngredient = numberOfIngredient;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
