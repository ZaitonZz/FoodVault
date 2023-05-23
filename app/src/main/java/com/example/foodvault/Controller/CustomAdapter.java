package com.example.foodvault.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.UserCRUD;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;
import com.example.foodvault.View.ActivityLogin;
import com.example.foodvault.View.ActivityViewRecipe;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Recipe> recipeListView;

    public CustomAdapter(Context context, ArrayList<Recipe> recipeListView) {
        this.context = context;
        this.recipeListView = recipeListView;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.recipeNameText.setText((String.format("%s", recipeListView.get(position).getRecipeName())));
        holder.recipeDesText.setText(String.format("%s", recipeListView.get(position).getDescription()));
        holder.chipRecipeCusine.setText(String.format("%s", recipeListView.get(position).getCuisine()));
        holder.chipCookTime.setText((String.format("Ready in %s minutes", recipeListView.get(position).getTime())));

        // set image
        String recipeName = recipeListView.get(position).getRecipeName();

        // modify string name to fit with title of each image
        String name = recipeName.replaceAll("\\s", "").toLowerCase();

        int resourceID = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.recipeImage.setImageResource(resourceID);

        String recipeCurrent = recipeListView.get(position).getRecipeName();

        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecipeDetails(recipeCurrent);
            }
        });

        // retrieve saved recipes of user then modify the image button
        Recipe selectedRec = recipeListView.get(position);
        UserDetails currentUser = UserCRUD.retrieveUserWithUsername(ActivityLogin.currentUserLogIn);
        if (currentUser.getSavedRecipes().contains(selectedRec)) {
            holder.saveButton.setSelected(true);
        }

        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.saveButton.isSelected()) {
                    holder.saveButton.setSelected(false);
                    // remove saved recipe of user
                    Controller.RecipeData.deleteUserSaveRecipe(currentUser, selectedRec);
                }
                else {
                    holder.saveButton.setSelected(true);
                    // add recipe to saved recipe of user
                    Controller.RecipeData.addUserSavedRecipe(currentUser, selectedRec);
                }


            }
        });

    }

    private void openRecipeDetails(String nameRecipe) {
        Intent intent = new Intent(context, ActivityViewRecipe.class);
        intent.putExtra("recipe", nameRecipe);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return recipeListView.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameText, recipeDesText;
        Chip chipRecipeCusine, chipCookTime;
        ImageView recipeImage;

        Button open;

        ImageButton saveButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameText = itemView.findViewById(R.id.recipe_name);
            recipeDesText = itemView.findViewById(R.id.recipe_description);

            chipRecipeCusine = itemView.findViewById(R.id.recipe_cuisine_chip);
            chipCookTime = itemView.findViewById(R.id.recipe_cook_time_chip);

            recipeImage = itemView.findViewById(R.id.recipe_image);

            open = itemView.findViewById(R.id.openRecipeButton);

            saveButton = itemView.findViewById(R.id.savedRecipeButton);
        }
    }
}
