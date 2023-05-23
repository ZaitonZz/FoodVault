package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodvault.Controller.CustomAdapter;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.R;

import java.util.ArrayList;

public class ShowRecipeData extends AppCompatActivity {

    RecyclerView recyclerView;
    public static ArrayList<Recipe> recipeListToShow;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe_data);

        recyclerView = findViewById(R.id.recyclerView);

        customAdapter = new CustomAdapter(ShowRecipeData.this, recipeListToShow);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowRecipeData.this));
    }
}