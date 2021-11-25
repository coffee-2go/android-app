package com.android.coffee2go.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.android.coffee2go.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @author Michal Pupák
 * **/

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.menuFragment,R.id.cartFragment,R.id.locationFragment,R.id.profileFragment).build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (navHostFragment != null) {

            NavController navController = navHostFragment.getNavController();

            // Setup NavigationUI here
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }

    }
}