package com.example.foodvault.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodvault.Controller.Controller;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.R;

import java.util.HashSet;
import java.util.Random;


public class FragmentHome extends Fragment {

    ImageView imageOne, imageTwo, imageThree, imageFour, imageFive;
    TextView labelOne, labelTwo, labelThree, labelFour, labelFive;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        imageOne = view.findViewById(R.id.recipeOneImage);
        imageTwo = view.findViewById(R.id.recipeTwoImage);
        imageThree = view.findViewById(R.id.recipeThreeImage);
        imageFour = view.findViewById(R.id.recipeFourImage);
        imageFive = view.findViewById(R.id.recipeFiveImage);

        labelOne = view.findViewById(R.id.recipeOneLabel);
        labelTwo = view.findViewById(R.id.recipeTwoLabel);
        labelThree = view.findViewById(R.id.recipeThreeLabel);
        labelFour = view.findViewById(R.id.recipeFourLabel);
        labelFive = view.findViewById(R.id.recipeFiveLabel);

        // generate random recipe list (5)
        // generate 5 random numbers from 0 to recipe database size
        int limit = Controller.RecipeData.getRecipeList().size();

        // use hash set to get unique numbers
        HashSet<Integer> uniqueNum = new HashSet<>();

        Random random = new Random();

        // generate numbers
        while(uniqueNum.size() < 5) {
            int randomNum = random.nextInt(limit);
            uniqueNum.add(randomNum);
        }

        // transfer numbers to array int for easy access
        int[] randomNumbersIndex = new int[5];
        int index = 0;
        for (int number: uniqueNum) {
            randomNumbersIndex[index] = number;
            index++;
        }

        // retrieve recipes
        Recipe recipeOne = Controller.RecipeData.getRecipeList().get(randomNumbersIndex[0]);
        Recipe recipeTwo = Controller.RecipeData.getRecipeList().get(randomNumbersIndex[1]);
        Recipe recipeThree = Controller.RecipeData.getRecipeList().get(randomNumbersIndex[2]);
        Recipe recipeFour = Controller.RecipeData.getRecipeList().get(randomNumbersIndex[3]);
        Recipe recipeFive = Controller.RecipeData.getRecipeList().get(randomNumbersIndex[4]);

        // set layout labels
        labelOne.setText(recipeOne.getRecipeName());
        labelTwo.setText(recipeTwo.getRecipeName());
        labelThree.setText(recipeThree.getRecipeName());
        labelFour.setText(recipeFour.getRecipeName());
        labelFive.setText(recipeFive.getRecipeName());

        // set layout image
        // modify string name to fit with title of each image
        String name = recipeOne.getRecipeName().replaceAll("\\s", "").toLowerCase();
        int resourceID = view.getContext().getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
        imageOne.setImageResource(resourceID);

        name = recipeTwo.getRecipeName().replaceAll("\\s", "").toLowerCase();
        resourceID = view.getContext().getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
        imageTwo.setImageResource(resourceID);

        name = recipeThree.getRecipeName().replaceAll("\\s", "").toLowerCase();
        resourceID = view.getContext().getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
        imageThree.setImageResource(resourceID);

        name = recipeFour.getRecipeName().replaceAll("\\s", "").toLowerCase();
        resourceID = view.getContext().getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
        imageFour.setImageResource(resourceID);

        name = recipeFive.getRecipeName().replaceAll("\\s", "").toLowerCase();
        resourceID = view.getContext().getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
        imageFive.setImageResource(resourceID);

        // onclick listener for each image
        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecipe(recipeOne.getRecipeName());
            }
        });

        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecipe(recipeTwo.getRecipeName());
            }
        });

        imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecipe(recipeThree.getRecipeName());
            }
        });

        imageFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecipe(recipeFour.getRecipeName());
            }
        });

        imageFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecipe(recipeFive.getRecipeName());
            }
        });

        return view;
    }

    private void showRecipe(String name) {
        Intent intent = new Intent(view.getContext(), ActivityViewRecipe.class);
        intent.putExtra("recipe", name);
        view.getContext().startActivity(intent);
    }

}