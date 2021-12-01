package com.android.coffee2go.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.coffee2go.R;
import com.android.coffee2go.helper.ConfigFirebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // config toolbar
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Coffee2go");
        setSupportActionBar(toolbar);

        // config auth object (for sign out option)
        auth = ConfigFirebase.getFirebaseAuth();

        // config bottom navigation
        configBottomNavigationView();

    }

    // responsible for creating the BottomNavigation
    private void configBottomNavigationView() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.menuFragment,R.id.cartFragment,R.id.locationFragment,R.id.profileFragment).build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainActivity_fragmentContainerView_NavHostFragment);

        if (navHostFragment != null) {

            NavController navController = navHostFragment.getNavController();

            // Setup NavigationUI here
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }
    }



    // --- top bar options menu ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // if item = logout
        if (item.getItemId() == R.id.menu_logout) {
            signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        try {
            auth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}