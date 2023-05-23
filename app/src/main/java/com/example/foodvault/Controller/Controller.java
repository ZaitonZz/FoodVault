package com.example.foodvault.Controller;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.foodvault.Model.Ingredient;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.RecipeCatalogue;
import com.example.foodvault.Model.UserCRUD;
import com.example.foodvault.Model.UserDetails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Controller {
    public static RecipeCatalogue RecipeData = new RecipeCatalogue();
    public static UserCRUD UserData = new UserCRUD();

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

        // read user data
        File file = new File(context.getExternalFilesDir(null),"userDetails.txt");
        if (!file.exists()) {
            try {
                InputStream inputStreamUser = assetManager.open("userDetails.txt");
                loadFromFileUserDetails(inputStreamUser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                loadFromFileUserDetails(context);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // read users' my recipe
        file = new File(context.getExternalFilesDir(null),"UsersMyRecipes.txt");
        if (!file.exists()) {
            try {
                InputStream inputStreamUser = assetManager.open("UsersMyRecipes.txt");
                loadFromFileMyRecipes(inputStreamUser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                loadFromFileMyRecipes(context);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void loadFromFileUserDetails(Context context) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(context.getExternalFilesDir(null),"userDetails.txt")));
        String lineUser = reader.readLine();
        while(lineUser != null) {
            String[] parts = lineUser.split(":");
            String firstName = parts[0];
            String lastName = parts[1];
            String username = parts[2];
            String email = parts[3];
            String password = parts[4];
            String savedRecipesString = parts[5];

            UserDetails ud = new UserDetails(firstName,lastName,username,email,password);

            // create saved recipes list
            String[] saveRecipes = {" "};
            try {
                saveRecipes = savedRecipesString.split(",");
            }
            catch (Exception e) {
            }

            if (saveRecipes[0].equals(" ")) {
                // pass
            }
            else {
                for (String s: saveRecipes) {
                    // retrieve recipe
                    Recipe r = RecipeData.retrieveRecipeByName(s);

                    // save to user's saved recipe list
                    RecipeData.addUserSavedRecipe(ud, r);
                }
            }

            UserData.createUserDetails(ud);
            lineUser = reader.readLine();
        }
        reader.close();
    }

    private static void loadFromFileUserDetails(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String lineUser = reader.readLine();

        while(lineUser != null) {
            String[] parts = lineUser.split(":");
            String firstName = parts[0];
            String lastName = parts[1];
            String username = parts[2];
            String email = parts[3];
            String password = parts[4];
            String savedRecipesString = parts[5];

            UserDetails ud = new UserDetails(firstName,lastName,username,email,password);

            // create saved recipes list
            String[] saveRecipes = {" "};
            try {
                saveRecipes = savedRecipesString.split(",");
            }
            catch (Exception e) {
            }

            if (saveRecipes[0].equals(" ")) {
                // pass
            }
            else {
                for (String s: saveRecipes) {
                    // retrieve recipe
                    Recipe r = RecipeData.retrieveRecipeByName(s);

                    // save to user's saved recipe list
                    RecipeData.addUserSavedRecipe(ud, r);
                }
            }

            UserData.createUserDetails(ud);
            lineUser = reader.readLine();
        }
        reader.close();
    }

    public static void saveToFile(Context context) throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(context.getExternalFilesDir(null), "userDetails.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        for (UserDetails ud : Controller.UserData.getUserList()) {

            writer.write(ud.getFirstName() + ":" + ud.getLastName() + ":" + ud.getUsername() + ":" + ud.getEmail()
                    + ":" + ud.getPassword() + ":" +  ud.getSaveRecipesAsString() + "\n");
        }
        writer.flush();
        writer.close();
    }

    public static void saveToFileMyRecipes(Context context) throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(context.getExternalFilesDir(null), "UsersMyRecipes.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        for(UserDetails ud: Controller.UserData.getUserList()) {
            // separator of user and list of recipe: #
            // string for username
            String username = ud.getUsername(); // element 0 of main array
            ArrayList<Recipe> myRecipes = ud.getMyRecipes();
            String[] listOfRecipesCombined = new String[myRecipes.size()]; // separator for each recipe: @
            int indexRec = 0;

            for (Recipe r: myRecipes) {
                // separator of recipe info is :
                String name = r.getRecipeName(); // string for recipe name

                String[] ingredients = new String[r.getIngredients().size()]; // separator of each ingredient is ;
                int index = 0;
                for (Ingredient i: r.getIngredients()) {
                    ingredients[index] = i.toString();
                    index++;
                }

                // string for ingredients
                String ingredientsCombined = String.join(";", ingredients);

                // string for instructions
                String steps = String.join(";", r.getInstructions());

                // string for categories
                String categ = String.join(";", r.getCategories());

                // string for nutrition
                String nutrition = String.join(";", r.getNutritionValues());

                // string for serving size
                String serving = r.getServingSize();

                // string for time
                String time = r.getTime();

                // string for cuisine
                String cuisine = r.getCuisine();

                // string for calorie
                String calorie = r.getCalorieAmount();

                // string for description
                String description = r.getDescription();

                // combined string for recipe
                String recipeInfoCombined = String.format("%s:%s:%s:%s:%s:%s:%s:%s:%s:%s", name, ingredientsCombined,
                        steps, categ,  nutrition, serving, time, cuisine, calorie, description);

                listOfRecipesCombined[indexRec] = recipeInfoCombined;
                indexRec++;
            }

            String myRecipesAsString = String.join("@", listOfRecipesCombined); // element 1 of main array
            writer.write(username + "#" + myRecipesAsString + "\n");
        }

        writer.flush();
        writer.close();
    }

    private static void loadFromFileMyRecipes(Context context) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(context.getExternalFilesDir(null),"UsersMyRecipes.txt")));
        String line = reader.readLine();

        while(line!=null) {
            String[] mainParts = line.split("#"); // # is delimiter for user and recipe
            String user = mainParts[0]; // user is at index 0;
            // retrieve user
            UserDetails userPerson = Controller.UserData.retrieveUserWithUsername(user);

            // index 1 are all list of recipes in a form of string with delimiter = "@"
            if (mainParts.length == 1) {
                // pass
            }
            else {
                String[] recipeInfoList = mainParts[1].split("@");
                // iterate through list of recipes and create recipes
                for(int i = 0 ; i < recipeInfoList.length; i++) {
                    String[] recipeParts = recipeInfoList[i].split(":");
                    String recipeName = recipeParts[0];
                    String[] ingredientList = recipeParts[1].split(";");

                    // create ingredients object then add to list
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    for(int j = 0; j < ingredientList.length; j++) {
                        // note: index 0 - qnty, 1 - unit, 2 - name, 3 - prep
                        String[] info = ingredientList[j].split("-");
                        ingredients.add(new Ingredient(info[0], info[1], info[2], info[3]));
                    }

                    String[] instructions = recipeParts[2].split(";");

                    // category list
                    String[] categories = recipeParts[3].split(";");

                    // nutrition val
                    String[] nutrition = recipeParts[4].split(";");

                    String servingSize = recipeParts[5];
                    String time = recipeParts[6];
                    String cuisine = recipeParts[7];
                    String caloriePerServ = recipeParts[8];
                    String description = recipeParts[9];

                    // create my recipe
                    Recipe recipe = new Recipe(recipeName, ingredients, instructions, categories, nutrition, servingSize, time,
                            cuisine, caloriePerServ, description);

                    // add to user's my recipe
                    Controller.RecipeData.addUserRecipe(userPerson, recipe);
                }

            }
            line = reader.readLine();
        }
        reader.close();

    }

    private static void loadFromFileMyRecipes(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        while(line!=null) {
            String[] mainParts = line.split("#"); // # is delimiter for user and recipe
            String user = mainParts[0]; // user is at index 0;
            // retrieve user
            UserDetails userPerson = Controller.UserData.retrieveUserWithUsername(user);

            // index 1 are all list of recipes in a form of string with delimiter = "@"
            if (mainParts.length == 1) {
                // pass
            }
            else {
                String[] recipeInfoList = mainParts[1].split("@");
                // iterate through list of recipes and create recipes
                for(int i = 0 ; i < recipeInfoList.length; i++) {
                    String[] recipeParts = recipeInfoList[i].split(":");
                    String recipeName = recipeParts[0];
                    String[] ingredientList = recipeParts[1].split(";");

                    // create ingredients object then add to list
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    for(int j = 0; j < ingredientList.length; j++) {
                        // note: index 0 - qnty, 1 - unit, 2 - name, 3 - prep
                        String[] info = ingredientList[j].split("-");
                        ingredients.add(new Ingredient(info[0], info[1], info[2], info[3]));
                    }

                    String[] instructions = recipeParts[2].split(";");

                    // category list
                    String[] categories = recipeParts[3].split(";");

                    // nutrition val
                    String[] nutrition = recipeParts[4].split(";");

                    String servingSize = recipeParts[5];
                    String time = recipeParts[6];
                    String cuisine = recipeParts[7];
                    String caloriePerServ = recipeParts[8];
                    String description = recipeParts[9];

                    // create my recipe
                    Recipe recipe = new Recipe(recipeName, ingredients, instructions, categories, nutrition, servingSize, time,
                            cuisine, caloriePerServ, description);

                    // add to user's my recipe
                    Controller.RecipeData.addUserRecipe(userPerson, recipe);
                }

            }
            line = reader.readLine();
        }
        reader.close();
    }

}
