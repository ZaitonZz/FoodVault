package com.example.foodvault.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.foodvault.R;
import com.example.foodvault.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    WriteFragment writeFragment = new WriteFragment();
    SearchFragment searchFragment = new SearchFragment();
    CalcFragment calcFragment = new CalcFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // set default
        replaceFragment(homeFragment);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
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
            }
        });

    }

    // change fragment method
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment).commit();
    }

//    private void enableFullscreen() {
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        );
//    }
}