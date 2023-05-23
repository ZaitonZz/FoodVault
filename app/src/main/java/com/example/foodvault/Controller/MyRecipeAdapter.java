package com.example.foodvault.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;
import com.example.foodvault.View.ActivityHome;
import com.example.foodvault.View.ActivityLogin;
import com.example.foodvault.View.ActivityViewRecipe;

import java.util.ArrayList;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.MyHolder> {
    private Context context;
    private ArrayList<Recipe> myRecipesToShow;

    public MyRecipeAdapter(Context context, ArrayList<Recipe> myRecipesToShow) {
        this.context = context;
        this.myRecipesToShow  =  myRecipesToShow;
    }

    @NonNull
    @Override
    public MyRecipeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_recipe_row, parent, false);

        return new MyRecipeAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecipeAdapter.MyHolder holder, int position) {
        holder.myRecipeName.setText(myRecipesToShow.get(position).getRecipeName());

        String current = myRecipesToShow.get(position).getRecipeName();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityViewRecipe.class);
                intent.putExtra("recipe", current);
                context.startActivity(intent);
            }
        });

        String name = myRecipesToShow.get(position).getRecipeName();
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDetails us = Controller.UserData.retrieveUserWithUsername(ActivityLogin.currentUserLogIn);
                Controller.RecipeData.deleteUserRecipe(us, name);

                ActivityHome.createMyRecipeSuccess = true;
                context.startActivity(new Intent(view.getContext(), ActivityHome.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return myRecipesToShow.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView myRecipeName;
        ImageButton delete;
        CardView cardView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            myRecipeName = itemView.findViewById(R.id.myRecipeName);
            cardView = itemView.findViewById(R.id.layoutCardViewMyRecipe);
            delete = itemView.findViewById(R.id.deleteButtonMyRecipe);
        }
    }
}
