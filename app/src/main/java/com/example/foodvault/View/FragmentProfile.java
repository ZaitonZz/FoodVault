package com.example.foodvault.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodvault.Controller.CustomAdapter;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.Model.UserCRUD;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;

import java.util.ArrayList;


public class FragmentProfile extends Fragment {

    TextView fullName, username;

    RecyclerView recyclerViewProf;
    ArrayList<Recipe> userFavRecipes;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fullName = view.findViewById(R.id.userFullNameText);
        username = view.findViewById(R.id.usernameText);
        recyclerViewProf = view.findViewById(R.id.recyclerViewProf);

        // retrieve current user
        UserDetails current = UserCRUD.retrieveUserWithUsername(ActivityLogin.currentUserLogIn);

        // set text view by user data
        fullName.setText(String.format("%s %s",current.getFirstName(), current.getLastName()));
        username.setText(String.format("@%s", current.getUsername()));

        // retrieve saved recipes
        userFavRecipes = current.getSavedRecipes();

        // show saved recipe
        customAdapter = new CustomAdapter(view.getContext(), userFavRecipes);
        recyclerViewProf.setAdapter(customAdapter);
        recyclerViewProf.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}