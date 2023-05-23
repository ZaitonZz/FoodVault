package com.example.foodvault.Controller;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.foodvault.Model.Ingredient;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.RecipeCatalogue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
    public static RecipeCatalogue RecipeData = new RecipeCatalogue();

    // load recipe data base
    public static void loadFromFile(Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("DataBase.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        while (line != null) {
            String[] recipeInfo = line.split(":");
            String recipeName = recipeInfo[0];
            String[] ingredientList = recipeInfo[1].split(";");

            // create ingredients object then add to list
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for(int i = 0; i < ingredientList.length; i++) {
                // note: index 0 - qnty, 1 - unit, 2 - name, 3 - prep
                String[] info = ingredientList[i].split("-");
                ingredients.add(new Ingredient(info[0], info[1], info[2], info[3]));
            }

            String[] instructions = recipeInfo[2].split(";");

            // category list
            String[] categories = recipeInfo[3].split(";");

            // nutrition val
            String[] nutrition = recipeInfo[4].split(";");

            String servingSize = recipeInfo[5];
            String time = recipeInfo[6];
            String cuisine = recipeInfo[7];
            String caloriePerServ = recipeInfo[8];
            String description = recipeInfo[9];

            // create recipe object then add to recipe list
            RecipeData.addRecipe(new Recipe(recipeName, ingredients, instructions, categories,
                    nutrition, servingSize, time, cuisine, caloriePerServ, description));
            line = reader.readLine();
        }
        reader.close();
    }


}
