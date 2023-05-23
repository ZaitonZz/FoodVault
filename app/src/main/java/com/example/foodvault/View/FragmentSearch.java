package com.example.foodvault.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodvault.Controller.CustomAdapter;
import com.example.foodvault.Controller.Controller;
import com.example.foodvault.Model.Ingredient;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;


public class FragmentSearch extends Fragment {

    Chip filterChip;

    FragmentFilters f = new FragmentFilters();
    BottomSheetDialog bottomSheetDialog;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    String[] item;

    CardView cardView;
    ImageView imageChicken, imageBeef, imageSeaFood, imageFilipino, imageDessert, imageVegetables;

    RecyclerView recyclerView;

    CustomAdapter customAdapter;

    String searchedRecipeString;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        item = new String[Controller.RecipeData.getRecipeList().size()];
        int index = 0;
        for (Recipe r: Controller.RecipeData.getRecipeList()) { // create list for items
            item[index] = r.getRecipeName();
            index++;
        }

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false);

        // for the search bar
        autoCompleteTextView = view.findViewById(R.id.searchEditText);
        adapterItems = new ArrayAdapter<String>(view.getContext(), R.layout.recipe_list, item);
        autoCompleteTextView.setAdapter(adapterItems);

        // select item listener
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchedRecipeString = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(view.getContext(), ActivityViewRecipe.class);
                intent.putExtra("recipe", searchedRecipeString);
                view.getContext().startActivity(intent);
            }
        });

        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    searchedRecipeString = autoCompleteTextView.getText().toString();

                    if (Controller.RecipeData.retrieveRecipeByName(searchedRecipeString) == null) {
                        Toast.makeText(view.getContext(),"No results", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(view.getContext(), ActivityViewRecipe.class);
                        intent.putExtra("recipe", searchedRecipeString);
                        view.getContext().startActivity(intent);
                        return true;
                    }

                }
                return false;
            }
        });

        // bottom sheet filter options
        filterChip = view.findViewById(R.id.chipFilter);
        bottomSheetDialog = new BottomSheetDialog(view.getContext(),R.style.BottomSheetDialogTheme);
        createDialog();

        // category view of each top categories
        imageChicken = view.findViewById(R.id.chickenCateg);
        imageBeef = view.findViewById(R.id.beefCategory);
        imageDessert = view.findViewById(R.id.dessertCateg);
        imageFilipino = view.findViewById(R.id.filipinoCateg);
        imageVegetables = view.findViewById(R.id.vegetablesCateg);
        imageSeaFood = view.findViewById(R.id.seaFoodCateg);

        // click listener for each category
        imageChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecipeData.recipeListToShow = Controller.RecipeData.filterByCategory("Chicken");
                startActivity(new Intent(getActivity(), ShowRecipeData.class));
            }
        });

        imageBeef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecipeData.recipeListToShow = Controller.RecipeData.filterByCategory("Beef");
                startActivity(new Intent(getActivity(), ShowRecipeData.class));
            }
        });

        imageSeaFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecipeData.recipeListToShow = Controller.RecipeData.filterByCategory("Seafood");
                startActivity(new Intent(getActivity(), ShowRecipeData.class));
            }
        });

        imageFilipino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecipeData.recipeListToShow = Controller.RecipeData.filterByCuisine("Filipino");
                startActivity(new Intent(getActivity(), ShowRecipeData.class));
            }
        });

        imageVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecipeData.recipeListToShow = Controller.RecipeData.filterByCategory("Vegetables");
                startActivity(new Intent(getActivity(), ShowRecipeData.class));
            }
        });

        imageDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecipeData.recipeListToShow = Controller.RecipeData.filterByCategory("Dessert");
                startActivity(new Intent(getActivity(), ShowRecipeData.class));
            }
        });

        // click listener to view filter options
        filterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });
        return view;
    }

    private void createDialog() { // bottom sheet dialog config
        View bottomSheetView = getLayoutInflater().inflate(R.layout.fragment_filters, null, false);
        bottomSheetDialog.setContentView(bottomSheetView);
        // entry chip for filter ingredient
        ChipGroup chipGroupIngredients = bottomSheetView.findViewById(R.id.ingredientsGroup);
        EditText ingredientNameText = bottomSheetView.findViewById(R.id.ingredientsListText);

        // set on key listener for edit text
        ingredientNameText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    ChipGroup chipGroupIngredients = bottomSheetView.findViewById(R.id.ingredientsGroup);
                    EditText ingredientNameText = bottomSheetView.findViewById(R.id.ingredientsListText);

                    // create
                    createInputChip(chipGroupIngredients, ingredientNameText.getText().toString());

                    // Clear the edit text
                    ingredientNameText.setText("");

                    return true;
                }

                return false;
            }
        });


        // filters
        Button searchFilter = bottomSheetView.findViewById(R.id.searchWithFilter);
        ChipGroup chipGroupMealTypes = bottomSheetView.findViewById(R.id.mealTypeGroup);
        ChipGroup chipGroupCuisine = bottomSheetView.findViewById(R.id.cuisineGroup);
        ChipGroup chipGroupCookTime = bottomSheetView.findViewById(R.id.cookTimeGroup);
        ChipGroup chipGroupNutrition = bottomSheetView.findViewById(R.id.nutritionGroup);

        searchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();

                // ingredients filter options
                // create ingredient object list
                ArrayList<Ingredient> ingredientsListNames = new ArrayList<>();
                for (int i = 0; i < chipGroupIngredients.getChildCount(); i++) {
                    View viewChild = chipGroupIngredients.getChildAt(i);
                    // check if the child view in chip group is a chip
                    if(viewChild instanceof Chip) {
                        Chip chip = (Chip) viewChild;
                        Ingredient temp = new Ingredient();
                        temp.setIngredientName(chip.getText().toString());
                        ingredientsListNames.add(temp);
                    }
                }

                // get checked chip
                int mealTypeSelected = chipGroupMealTypes.getCheckedChipId();
                int cuisineSelected = chipGroupCuisine.getCheckedChipId();
                int cookTimeSelected = chipGroupCookTime.getCheckedChipId();
                int nutritionSelected = chipGroupNutrition.getCheckedChipId();

                // initialize needed arguments for filter operations
                String mealType, cuisine, nutrition;
                double cookTime = 0;

                // meal type checker
                if (mealTypeSelected == View.NO_ID) {
                    mealType = "";
                }
                else {
                    Chip mealTypeChip = bottomSheetView.findViewById(mealTypeSelected);
                    mealType = mealTypeChip.getText().toString();
                }

                // cuisine checker
                if (cuisineSelected == View.NO_ID) {
                    cuisine = "";
                }
                else {
                    Chip cuisineChip = bottomSheetView.findViewById(cuisineSelected);
                    cuisine = cuisineChip.getText().toString();
                }

                // nutrition checker
                if (nutritionSelected == View.NO_ID) {
                    nutrition = "";
                }
                else {
                    Chip nutritionChip = bottomSheetView.findViewById(nutritionSelected);
                    nutrition = nutritionChip.getText().toString();
                }

                // cook time checker
                if (cookTimeSelected == View.NO_ID) {
                    cookTime = 0;
                }
                else {
                    Chip cookTimeChip = bottomSheetView.findViewById(cookTimeSelected);
                    String cookTimeString = cookTimeChip.getText().toString();

                    if (cookTimeString.equalsIgnoreCase("Under 30 minutes")) {
                        cookTime = 30;
                    }
                    else if (cookTimeString.equalsIgnoreCase("Under an hour")) {
                        cookTime = 60;
                    }
                }

                if (Controller.RecipeData.filterOperation(
                        ingredientsListNames, mealType, cuisine, cookTime, nutrition).isEmpty()) {
                    Toast.makeText(view.getContext(),"No results", Toast.LENGTH_SHORT).show();
                }
                else {
                    ShowRecipeData.recipeListToShow = Controller.RecipeData.filterOperation(
                            ingredientsListNames, mealType, cuisine, cookTime, nutrition);

                    startActivity(new Intent(getActivity(), ShowRecipeData.class));
                }

            }
        });
    }
    private void createInputChip(ChipGroup chipGroup, String text) {
        Chip chip = new Chip(view.getContext());
        chip.setText(text);
        chip.setCloseIconVisible(true);

        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(chip);
            }
        });

        chipGroup.addView(chip);
    }


}