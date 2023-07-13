package com.example.ourrecipe;

public class ClassUser {
    int avt;
    String name;
    String bio;
    String email;
    String phone;

    public ClassUser(String name, String bio, String email, String phone) {
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClassUser(int avt, String name, String email) {
        this.avt = avt;
        this.name = name;
        this.email = email;
    }
    public ClassUser(String name, String email) {
        this.name = name;
        this.email = email;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
