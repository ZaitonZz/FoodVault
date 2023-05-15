package com.example.foodvault.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodvault.Controller.RecipeController;
import com.example.foodvault.Controller.RecipeSearch;
import com.example.foodvault.Model.Recipe;
import com.example.foodvault.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

public class ActivityHome extends AppCompatActivity {

    FragmentHome homeFragment = new FragmentHome();
    FragmentWrite writeFragment = new FragmentWrite();
    FragmentSearch searchFragment = new FragmentSearch();
    FragmentCalc calcFragment = new FragmentCalc();
    FragmentProfile profileFragment = new FragmentProfile();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            RecipeController.loadFromFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        for (Recipe r: RecipeController.RecipeData.getRecipeList()) {
            System.out.println(r.getRecipeName());
        }
        // set default
        replaceFragment(homeFragment);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            switch (id) {
                case R.id.navigation_home:
                    replaceFragment(homeFragment);
                    bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                    return true;
                case R.id.navigation_write:
                    replaceFragment(writeFragment);
                    bottomNavigationView.getMenu().findItem(R.id.navigation_write).setChecked(true);
                    return true;
                case R.id.navigation_search:
                    replaceFragment(searchFragment);
                    bottomNavigationView.getMenu().findItem(R.id.navigation_search).setChecked(true);
                    return true;
                case R.id.navigation_calculator:
                    replaceFragment(calcFragment);
                    bottomNavigationView.getMenu().findItem(R.id.navigation_calculator).setChecked(true);
                    return true;
                case R.id.navigation_profile:
                    replaceFragment(profileFragment);
                    bottomNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                    return true;
            }
            return false;
        });

    }

    // change fragment method
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment).commit();
    }

}