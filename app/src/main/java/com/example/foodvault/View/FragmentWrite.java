package com.example.foodvault.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodvault.Controller.Controller;
import com.example.foodvault.Controller.MyRecipeAdapter;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentWrite extends Fragment {
    FloatingActionButton addRecipeButton;
    MyRecipeAdapter myRecipeAdapter;
    ArrayList<Recipe> myRecipes;
    RecyclerView myRecipeView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_write, container, false);

        myRecipeView = view.findViewById(R.id.recyclerViewMyRecipe);
        addRecipeButton = view.findViewById(R.id.addRecipeButton);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ActivityAddMyRecipe.class));
            }
        });

        // my recipe list to show
        UserDetails currentUser = Controller.UserData.retrieveUserWithUsername(ActivityLogin.currentUserLogIn);
        myRecipes = currentUser.getMyRecipes();

        // show list
        myRecipeAdapter = new MyRecipeAdapter(view.getContext(), myRecipes);
        myRecipeView.setAdapter(myRecipeAdapter);
        myRecipeView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
    
}