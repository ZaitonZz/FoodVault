package com.example.foodvault.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodvault.Controller.Controller;
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
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false); //edge-to-edge frame
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // set default
        replaceFragment(new FragmentHome());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            switch (id) {
                case R.id.navigation_home:
                    replaceFragment(new FragmentHome());
                    bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                    return true;
                case R.id.navigation_write:
                    replaceFragment(new FragmentWrite());
                    bottomNavigationView.getMenu().findItem(R.id.navigation_write).setChecked(true);
                    return true;
                case R.id.navigation_search:
                    replaceFragment(new FragmentSearch());
                    bottomNavigationView.getMenu().findItem(R.id.navigation_search).setChecked(true);
                    return true;
                case R.id.navigation_calculator:
                    replaceFragment(new FragmentCalc());
                    bottomNavigationView.getMenu().findItem(R.id.navigation_calculator).setChecked(true);
                    return true;
                case R.id.navigation_profile:
                    replaceFragment(new FragmentProfile());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            Controller.saveToFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            Controller.saveToFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}