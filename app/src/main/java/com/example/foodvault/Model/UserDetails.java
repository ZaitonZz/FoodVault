package com.example.foodvault.Model;

import java.util.ArrayList;

public class UserDetails extends Person{
    private String username, email, password;
    private ArrayList<Recipe> myRecipes, savedRecipes;
    public UserDetails(){
        super();
        username = "";
        email = "";
        password ="";
        myRecipes = new ArrayList<Recipe>();
        savedRecipes = new ArrayList<Recipe>();
    }
    public UserDetails(String firstName, String lastName, String username, String email, String password){
        super(firstName, lastName);
        this.username = username;
        this.email = email;
        this.password = password;
        myRecipes = new ArrayList<Recipe>();
        savedRecipes = new ArrayList<Recipe>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public void setMyRecipes(ArrayList<Recipe> myRecipes) {
        this.myRecipes = myRecipes;
    }

    public ArrayList<Recipe> getSavedRecipes() {
        return savedRecipes;
    }

    public void setSavedRecipes(ArrayList<Recipe> savedRecipes) {
        this.savedRecipes = savedRecipes;
    }
}
