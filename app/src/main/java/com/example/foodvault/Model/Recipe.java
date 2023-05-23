package com.example.foodvault.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String recipeName, servingSize, cuisine, calorieAmount, time, description;
    private String[] instructions, categories, nutritionValues;
    private ArrayList<Ingredient> ingredients;

    public Recipe() {
        recipeName = "";
        servingSize = "";
        cuisine = "";
        calorieAmount = "";
        instructions = new String[]{""};
        categories = new String[]{""};
        nutritionValues = new String[]{""};
        ingredients = new ArrayList<Ingredient>();
        time = "";
        description = "";
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
        time = "";
    }

    public Recipe(String recipeName, ArrayList<Ingredient> ingredients, String[] instructions,
                  String[] categories, String[] nutritionValues, String servingSize, String time,
                  String cuisine, String calorieAmount, String description) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.categories = categories;
        this.nutritionValues = nutritionValues;
        this.servingSize = servingSize;
        this.time = time;
        this.cuisine = cuisine;
        this.calorieAmount = calorieAmount;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {return description;}

    public void setDescription() {this.description = description;}

    public void setTime(String time) {
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

    public boolean equals(Recipe r) {
        if (this.getRecipeName().equalsIgnoreCase(r.getRecipeName())) {
            return true;
        }
        else {
            return false;
        }
    }
}
