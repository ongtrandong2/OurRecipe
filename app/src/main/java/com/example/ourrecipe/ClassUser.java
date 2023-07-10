package com.example.ourrecipe;

public class ClassUser {
    int avt;
    String name;
    String description;
    String email;
    String[] keyRecipe;

    public ClassUser(int avt, String name, String description, String email, String[] keyRecipe) {
        this.avt = avt;
        this.name = name;
        this.description = description;
        this.email = email;
        this.keyRecipe = keyRecipe;
    }


    public int getAvt() {

        return avt;
    }

    public void setAvt(int avt) {

        this.avt = avt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getKeyRecipe() {
        return keyRecipe;
    }

    public void setKeyRecipe(String[] keyRecipe) {
        this.keyRecipe = keyRecipe;
    }



}
