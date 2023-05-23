package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodvault.Controller.Controller;
import com.example.foodvault.Model.Ingredient;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;


public class ActivityViewRecipe extends AppCompatActivity {

    TextView recipeNameText, cuisineText, serveText, timeText, calorieText, ingredientsText, stepsText;

    ChipGroup chipGroupCateg, chipGroupNutrition;

    ImageView imageRecipe;

    String[] steps, nutrition, category;
    ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        recipeNameText = findViewById(R.id.recipeName);
        cuisineText = findViewById(R.id.recipeCuisine);
        serveText = findViewById(R.id.numServeText);
        timeText = findViewById(R.id.cookTimeText);
        calorieText = findViewById(R.id.calcAmountText);
        ingredientsText = findViewById(R.id.ingredientsListTextView);
        stepsText = findViewById(R.id.instructionsListTextView);
        chipGroupCateg = findViewById(R.id.categoryTagsChipGroup);
        chipGroupNutrition = findViewById(R.id.nutritionTagsChipGroup);
        imageRecipe = findViewById(R.id.recipeImage);

        getAndSetIntentData();
    }


    private void getAndSetIntentData() {
            Intent intent = getIntent();
            String recipeName = intent.getStringExtra("recipe");
            Recipe recipeTemp = Controller.RecipeData.retrieveRecipeByName(recipeName);
            if (recipeTemp == null) {
                UserDetails us = Controller.UserData.retrieveUserWithUsername(ActivityLogin.currentUserLogIn);
                recipeTemp = Controller.RecipeData.retrieveUserRecipe(us,recipeName);
            }

            // set data
            recipeNameText.setText(recipeTemp.getRecipeName());
            cuisineText.setText(String.format("%s cuisine",recipeTemp.getCuisine()));
            serveText.setText(String.format("%s serve", recipeTemp.getServingSize()));

            String[] timeTemp = recipeTemp.getTime().split("  ");
            timeText.setText(String.format("%s min",  timeTemp[0]));
            calorieText.setText(String.format("%s kcal", recipeTemp.getCalorieAmount()));

            steps = recipeTemp.getInstructions();
            String stepsString = "";
            for (int i = 1; i < steps.length + 1; i++) {
                stepsString = stepsString + i + ". " + steps[i-1] + "\n\n";
            }
            stepsText.setText(stepsString);

            ingredients = recipeTemp.getIngredients();
            String ingredientsString = "";
            for (Ingredient i: ingredients) {
               ingredientsString = ingredientsString + i.getQuantity() + " " + i.getUnit() + " " +
                        i.getIngredientName() + " " + i.getPreparation() + "\n";
            }
            ingredientsText.setText(ingredientsString);

            category = recipeTemp.getCategories();
            for (int i = 0; i < category.length; i++) {
                Chip chipTemp = new Chip(this);
                chipTemp.setText(category[i]);
                chipTemp.setClickable(false);
                chipGroupCateg.addView(chipTemp);
            }

            nutrition = recipeTemp.getNutritionValues();
            for (int i = 0; i < nutrition.length; i++) {
                Chip chipTemp = new Chip(this);
                chipTemp.setText(nutrition[i]);
                chipTemp.setClickable(false);
                chipGroupNutrition.addView(chipTemp);
            }

            // modify string name to fit with title of each image
            String name = recipeName.replaceAll("\\s", "").toLowerCase();

            int resourceID = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
            if (resourceID == View.NO_ID) {
                resourceID = this.getResources().getIdentifier("myrecipebackground", "drawable", this.getPackageName());
            }
            imageRecipe.setImageResource(resourceID);


    }
}