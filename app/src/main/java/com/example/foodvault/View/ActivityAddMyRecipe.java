package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodvault.Controller.Controller;
import com.example.foodvault.Model.Ingredient;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddMyRecipe extends AppCompatActivity {

    EditText recipeName, recDescription, ingredName, ingredQnty, ingredUnit,
            ingredPrep, ingredDirections, recTime, recCalorie, recServings;

    ChipGroup ingListChips, mealType, nutritionType, cuisineGroup;

    Button addIngredient, createRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_recipe);

        recipeName = findViewById(R.id.myRecipeNameText);
        recDescription = findViewById(R.id.myRecipeDescription);
        ingredName = findViewById(R.id.myRecipeIngredient);
        ingredQnty = findViewById(R.id.myRecipeIngQnty);
        ingredUnit = findViewById(R.id.myRecipeIngUnit);
        ingredPrep = findViewById(R.id.myRecipeIngPrep);
        ingredDirections = findViewById(R.id.myRecipeStepsText);
        recTime = findViewById(R.id.myRecipeTimeText);
        recCalorie = findViewById(R.id.myRecipeCalorieText);
        recServings = findViewById(R.id.myRecipeServings);

        mealType = findViewById(R.id.mealTypeGroupMyRecipe);
        nutritionType = findViewById(R.id.nutritionGroupMyRecipe);
        cuisineGroup = findViewById(R.id.cuisineGroupMyRecipe);

        ingListChips = findViewById(R.id.myRecipeIngChipGroup);

        // initialize ingredients list
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        addIngredient = findViewById(R.id.myRecipeAddIngButton);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chip chip = new Chip(view.getContext());

                String name = ingredName.getText().toString();
                String quantity = ingredQnty.getText().toString();
                String unit = ingredUnit.getText().toString();
                String prep = ingredPrep.getText().toString();
                if(prep.equals("")) {
                    prep = " ";
                }

                String text = quantity + " " + unit + " " + name + " " + prep;

                // create ingredient
                Ingredient ing = new Ingredient(quantity, unit, name, prep);
                chip.setText(text);

                if (name.equals("") || quantity.equals("") || unit.equals("")) {
                    Toast.makeText(view.getContext(),"Please enter ingredient name, quantity, and unit", Toast.LENGTH_SHORT).show();
                }
                else {

                    ingListChips.addView(chip);

                    // add ingredient to list
                    ingredientsList.add(ing);

                    ingredName.setText("");
                    ingredQnty.setText("");
                    ingredUnit.setText("");
                    ingredPrep.setText("");
                }

                chip.setCloseIconVisible(true);

                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ingListChips.removeView(chip);
                        // remove ingredient from list
                        ingredientsList.remove(ing);
                    }
                });



            }
        });

        // create recipe
        createRecipe = findViewById(R.id.createMyRecipeButton);
        createRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // retrieve info

                // recipe name
                String name = recipeName.getText().toString();

                // description
                String description = recDescription.getText().toString();
                if (description.equals("")) {
                    description = " ";
                }

                // ingredients is ingredientsList

                // directions
                String directionsString = ingredDirections.getText().toString();
                if (directionsString.equals("")) {
                    directionsString = " ";
                }
                String[] steps = {directionsString};

                // time
                String time = recTime.getText().toString();
                if (time.equals("")) {
                    time = " ";
                }

                // calorie
                String calorie = recCalorie.getText().toString();
                if(calorie.equals("")) {
                    calorie = " ";
                }

                // servings
                String servings = recServings.getText().toString();
                if (servings.equals("")) {
                    servings = " ";
                }

                // meal type or category
                int idChip = mealType.getCheckedChipId();

                String mealTypeString = " ";

                if (idChip != View.NO_ID) {
                    Chip chip = findViewById(idChip);
                    mealTypeString = chip.getText().toString();
                }

                String[] category = {mealTypeString};

                // nutrition value
                List<Integer> chipIds = nutritionType.getCheckedChipIds();
                String[] nutrition = {" "};
                if (chipIds.isEmpty()) {
                    // pass
                }
                else {
                    nutrition = new String[chipIds.size()];
                    int index = 0;
                    for (Integer i: chipIds) {
                        Chip chip = findViewById(i);
                        nutrition[index] = chip.getText().toString();
                        index++;
                    }
                }


                // cuisine
                int chipCuisineId = cuisineGroup.getCheckedChipId();
                String cuisine = " ";

                if (chipCuisineId != View.NO_ID) {
                    Chip chip = findViewById(idChip);
                    cuisine = chip.getText().toString();
                }

                // check fields
                if (name.equals("") || ingredDirections.getText().toString().equals("") || ingredientsList.isEmpty()) {
                    Toast.makeText(view.getContext(),"Please enter recipe name, ingredient, and directions.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // create recipe
                    Recipe r = new Recipe(name, ingredientsList, steps, category, nutrition, servings, time, cuisine, calorie, description);
                    UserDetails ud = Controller.UserData.retrieveUserWithUsername(ActivityLogin.currentUserLogIn);

                    Controller.RecipeData.addUserRecipe(ud, r);
                    Toast.makeText(view.getContext(),"Recipe added successfully.", Toast.LENGTH_SHORT).show();
                    ActivityHome.createMyRecipeSuccess = true;

                    startActivity(new Intent(view.getContext(), ActivityHome.class));

                }

            }
        });


    }
}