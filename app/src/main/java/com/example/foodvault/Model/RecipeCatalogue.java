package com.example.foodvault.Model;

import android.widget.ArrayAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class RecipeCatalogue {
    private ArrayList<Recipe> recipeList;

    public RecipeCatalogue() {
        recipeList = new ArrayList<>();
    }

    public ArrayList<Recipe> getRecipeList() {return recipeList;}
    // Retrieve a specific recipe object from the recipe list
    public Recipe retrieveRecipe(Recipe recipe) {
        for (Recipe r : recipeList) {
            if (r.equals(recipe)) {
                return r;
            }
        }
        return null;
    }

    public Recipe retrieveRecipeByName(String recipeName) {
        Recipe rec = new Recipe();
        rec.setRecipeName(recipeName);

        Recipe r = retrieveRecipe(rec);

        return r;
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
            if (r.getRecipeName().toLowerCase().contains(recipeName.toLowerCase())) {
                return r;
            }
        }
        return null;
    }

    // filter by list of ingredients
    public ArrayList<Recipe> filterByIngredients(ArrayList<Ingredient> ingredientList) {
        ArrayList<Recipe> result = new ArrayList<>();
        for (Recipe r: recipeList) {
            if (r.getIngredients().containsAll(ingredientList)) {
                result.add(r);
            }
        }

        return result;
    }

    // filter by cuisine
    public ArrayList<Recipe> filterByCuisine(String cuisine) {
        ArrayList<Recipe> result = new ArrayList<>();

        for (Recipe r: recipeList) {
            if (r.getCuisine().equalsIgnoreCase(cuisine)) {
                result.add(r);
            }
        }

        return result;
    }

    // filter by category
    public ArrayList<Recipe> filterByCategory(String category) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r : recipeList) {
            if (Arrays.asList(r.getCategories()).contains(category)) {
                recipes.add(r);
                break;
            }
        }
        return recipes;
    }

    // filter by cooktime (under n minutes)
    public ArrayList<Recipe> filterByCookTime(double time) { // time should be in minutes
        ArrayList<Recipe> result = new ArrayList<>();

        for (Recipe r: recipeList) {
            String[] recipeTimeAsString = r.getTime().split(" ");
            double recipeTime = Double.parseDouble(recipeTimeAsString[0]); // first element contains the number, second contains the unit

            if (recipeTime <= time) {
                result.add(r);
            }
        }

        return result;
    }

    // filter by nutrition value
    public ArrayList<Recipe> filterByNutrition(String nutrition) {
        ArrayList<Recipe> result = new ArrayList<>();

        for (Recipe r: recipeList) {
            if (Arrays.asList(r.getNutritionValues()).contains(nutrition)) {
                result.add(r);
            }
        }
        return result;
    }

    // find common among all selected filters
    public ArrayList<Recipe> filterOperation(ArrayList<Ingredient> ingList, String category, String cuisine, double time, String nutrition) {
        ArrayList<Recipe> filterIngr = filterByIngredients(ingList);
        ArrayList<Recipe> filterCat = filterByCategory(category);
        ArrayList<Recipe> filterCus = filterByCuisine(cuisine);
        ArrayList<Recipe> filterCook = filterByCookTime(time);
        ArrayList<Recipe> filterNut = filterByNutrition(nutrition);

        ArrayList<Recipe> result = new ArrayList<>();

        // find the common recipes
        for (Recipe r: recipeList) {
            if (filterIngr.contains(r) && filterCat.contains(r) && filterCus.contains(r)
                    && filterCook.contains(r) && filterNut.contains(r)) {
                result.add(r);
            }
        }

        return result;
    }

}