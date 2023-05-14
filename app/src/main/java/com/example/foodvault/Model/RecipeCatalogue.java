package com.example.foodvault.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeCatalogue {
    private ArrayList<Recipe> recipeList;

    public RecipeCatalogue() {
        recipeList = new ArrayList<>();
    }

    // Retrieve all recipes in a given category
    public ArrayList<Recipe> getRecByCateg(String[] categories) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r : recipeList) {
            for (String category : categories) {
                if (Arrays.asList(r.getCategories()).contains(category)) {
                    recipes.add(r);
                    break;
                }
            }
        }
        return recipes;
    }

    // Retrieve a specific recipe object from the recipe list
    public Recipe retrieveRecipe(Recipe recipe) {
        for (Recipe r : recipeList) {
            if (r.equals(recipe)) {
                return r;
            }
        }
        return null;
    }

    // Add a new recipe to the recipe list
    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }

    // Retrieve a recipe object from a specific user's recipe list
    public Recipe retrieveUserRecipe(UserDetails user, Recipe recipe) {
        if (user.getMyRecipes().contains(recipe)) {
            int index = user.getMyRecipes().indexOf(recipe);
            return user.getMyRecipes().get(index);
        }
        return null;
    }

    public void updateUserRecipe(UserDetails user, Recipe oldRecipe, Recipe newRecipe) {
        if (user.getMyRecipes().contains(oldRecipe)) {
            int index = user.getMyRecipes().indexOf(oldRecipe);
            user.getMyRecipes().set(index, newRecipe);
        }
    }

    public void deleteUserRecipe(UserDetails user, Recipe recipe) {
        user.getMyRecipes().remove(recipe);
    }

    public void addUserRecipe(UserDetails user, Recipe recipe) {
        user.getMyRecipes().add(recipe);
    }


    // Search for a recipe in the recipe list based on its name
    public Recipe searchRecipe(String recipeName) {
        for (Recipe r : recipeList) {
            if (r.getRecipeName().equalsIgnoreCase(recipeName)) {
                return r;
            }
        }
        return null;
    }
}