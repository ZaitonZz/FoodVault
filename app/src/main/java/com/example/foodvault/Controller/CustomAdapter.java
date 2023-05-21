package com.example.foodvault.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodvault.Model.Recipe;
import com.example.foodvault.R;
import com.google.android.material.chip.Chip;

import java.lang.reflect.Array;
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
        holder.chipCookTime.setText((String.format("Ready in %s", recipeListView.get(position).getTime())));

        // set image
        String recipeName = recipeListView.get(position).getRecipeName();

        // modify string name to fit with title of each image
        String name = recipeName.replaceAll("\\s", "").toLowerCase();


        int resourceID = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.recipeImage.setImageResource(resourceID);

    }

    @Override
    public int getItemCount() {
        return recipeListView.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameText, recipeDesText;
        Chip chipRecipeCusine, chipCookTime;
        ImageView recipeImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameText = itemView.findViewById(R.id.recipe_name);
            recipeDesText = itemView.findViewById(R.id.recipe_description);

            chipRecipeCusine = itemView.findViewById(R.id.recipe_cuisine_chip);
            chipCookTime = itemView.findViewById(R.id.recipe_cook_time_chip);

            recipeImage = itemView.findViewById(R.id.recipe_image);
        }
    }
}
