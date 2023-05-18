package com.example.foodvault.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Arrays;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodvault.Controller.RecipeController;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.R;


public class FragmentCalc extends Fragment {

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    String[] item;

    Button calculateButton;
    EditText numOfServings;
    TextView calResult, calPerServing;
    Recipe selectedRecipe;

    String recipeSelectedString = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        item = new String[RecipeController.RecipeData.getRecipeList().size()];
        int index = 0;
        for (Recipe r: RecipeController.RecipeData.getRecipeList()) { // create list for items
            item[index] = r.getRecipeName();
            index++;
        }


        View view = inflater.inflate(R.layout.fragment_calc, container, false);
        autoCompleteTextView = view.findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(view.getContext(), R.layout.recipe_list, item);

        autoCompleteTextView.setAdapter(adapterItems);

        // variables needed
        numOfServings = view.findViewById(R.id.numServingText);
        calResult = view.findViewById(R.id.calcResult);
        calPerServing = view.findViewById(R.id.calPerServeText);
        calculateButton = view.findViewById(R.id.buttonCalculate);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                recipeSelectedString = adapterView.getItemAtPosition(i).toString();
                // show calorie content of recipe
                selectedRecipe = RecipeController.RecipeData.retrieveRecipeByName(recipeSelectedString);
                calPerServing.setText(selectedRecipe.getCalorieAmount());
            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!recipeSelectedString.equals("") || !numOfServings.getText().toString().equals("")) {

                    if (recipeSelectedString.equals("")) {
                        Toast.makeText(view.getContext(),"Please select a food!", Toast.LENGTH_SHORT).show();
                    }
                    else if (numOfServings.getText().toString().equals("")) {
                        Toast.makeText(view.getContext(),"Please enter number of servings!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // calculate total calorie
                        double result = Double.parseDouble(numOfServings.getText().toString()) *
                                Double.parseDouble(selectedRecipe.getCalorieAmount());

                        // show result
                        calResult.setText(String.format("%.0f", result));
                    }

                }
                else {
                    // pass
                }
            }
        });

        return view;

    }
}