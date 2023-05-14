package com.example.foodvault.Model;

import java.util.ArrayList;

public class Recipe {
    private String recipeName, servingSize, cuisine, calorieAmount;
    private String[] instructions, categories, nutritionValues;
    private ArrayList<Ingredient> ingredients;
    private double time;

    public Recipe() {
        recipeName = "";
        servingSize = "";
        cuisine = "";
        calorieAmount = "";
        instructions = new String[]{""};
        categories = new String[]{""};
        nutritionValues = new String[]{""};
        ingredients = new ArrayList<Ingredient>();
        time = 0.0;
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        servingSize = "";
        cuisine = "";
        calorieAmount = "";
        instructions = new String[]{""};
        categories = new String[]{""};
        nutritionValues = new String[]{""};
        ingredients = new ArrayList<Ingredient>();
        time = 0.0;
    }

    public Recipe(String recipeName, ArrayList<Ingredient> ingredients, String[] instructions,
                  String[] categories, String[] nutritionValues, String servingSize, double time,
                  String cuisine, String calorieAmount) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.categories = categories;
        this.nutritionValues = nutritionValues;
        this.servingSize = servingSize;
        this.time = time;
        this.cuisine = cuisine;
        this.calorieAmount = calorieAmount;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCalorieAmount() {
        return calorieAmount;
    }

    public void setCalorieAmount(String calorieAmount) {
        this.calorieAmount = calorieAmount;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getNutritionValues() {
        return nutritionValues;
    }

    public void setNutritionValues(String[] nutritionValues) {
        this.nutritionValues = nutritionValues;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
