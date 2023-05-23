package com.example.foodvault.Model;

import java.util.ArrayList;
import java.util.Arrays;

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

        for (Recipe r: recipeList) {
            if (r.getRecipeName().equalsIgnoreCase(recipeName)) {return r;}
        }

        return null;
    }

    // Add a new recipe to the recipe list
    public void addRecipe(Recipe recipe) {
        if(retrieveRecipe(recipe) == null) {
            recipeList.add(recipe);
        }

    }

    // Retrieve a recipe object from a specific user's recipe list
    public Recipe retrieveUserRecipe(UserDetails user, String myRecipe) {
        for (Recipe r: user.getMyRecipes()) {
            if (r.getRecipeName().equalsIgnoreCase(myRecipe)) {
                return r;
            }
        }
        return null;
    }

    public void updateUserRecipe(UserDetails user, Recipe oldRecipe, Recipe newRecipe) {
        if (user.getMyRecipes().contains(oldRecipe)) {
            int index = user.getMyRecipes().indexOf(oldRecipe);
            user.getMyRecipes().set(index, newRecipe);
        }
    }

    public void deleteUserRecipe(UserDetails user, String name) {
        for(Recipe r: user.getMyRecipes()) {
            if (r.getRecipeName().equalsIgnoreCase(name)) {
                user.getMyRecipes().remove(r);
            }
        }
    }

    public void addUserRecipe(UserDetails user, Recipe recipe) {
        if (retrieveUserRecipe(user, recipe.getRecipeName()) == null) {
            user.getMyRecipes().add(recipe);
        }

    }

    public void addUserSavedRecipe(UserDetails user, Recipe recipe) {

        user.getSavedRecipes().add(recipe);


    }

    public void deleteUserSavedRecipe(UserDetails user, Recipe recipe) {
        user.getSavedRecipes().remove(recipe);
    }

    // filter by list of ingredients
    public ArrayList<Recipe> filterByIngredients(ArrayList<Ingredient> ingredientList) {
        ArrayList<Recipe> result = new ArrayList<>();

        if (ingredientList.isEmpty()) {
            return recipeList;
        }

        int limit = ingredientList.size();

        // create string for ingredients
        String[] ingStringArr = new String[limit];
        int index = 0;
        for (Ingredient ingredient: ingredientList) {
            ingStringArr[index] = ingredient.getIngredientName();
            index++;
        }

        int counter = 0;
        for (Recipe r: recipeList) {

            counter = 0;
            String[] tempIngList = new String[r.getIngredients().size()];
            int indexTemp = 0;
            for (Ingredient i: r.getIngredients()) {
                tempIngList[indexTemp] = i.getIngredientName();
                indexTemp++;
            }

            // check
            if (Arrays.asList(tempIngList).containsAll(Arrays.asList(ingStringArr))) {
                result.add(r);
            }

        }

        return result;
    }

    // filter by cuisine
    public ArrayList<Recipe> filterByCuisine(String cuisine) {
        ArrayList<Recipe> result = new ArrayList<>();

        if (cuisine == "") {
            return recipeList;
        }

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


        if (category == "") {
            return recipeList;
        }

        for (Recipe r : recipeList) {
            if (Arrays.asList(r.getCategories()).contains(category)) {
                recipes.add(r);
            }
        }

        return recipes;
    }

    // filter by cooktime (under n minutes)
    public ArrayList<Recipe> filterByCookTime(double time) { // time should be in minutes
        ArrayList<Recipe> result = new ArrayList<>();

        if(time == 0) {
            return recipeList;
        }

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

        if (nutrition == "") {
            return recipeList;
        }

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