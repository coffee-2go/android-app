package com.android.coffee2go.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.coffee2go.MainMenu;
import com.android.coffee2go.R;
import com.android.coffee2go.helper.FirebaseConfig;
import com.android.coffee2go.view.fragments.CartFragment;
import com.android.coffee2go.view.fragments.LocationFragment;
import com.android.coffee2go.view.fragments.MenuFragment;
import com.android.coffee2go.view.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // config toolbar
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Coffeee2go");
        setSupportActionBar(toolbar);

        // config object
        auth = FirebaseConfig.getFirebaseAuth();

        // config bottom navigation
        configBottomNavigationView();
        // when first opening, open MenuFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //TODO change back to MenuFragment
        fragmentTransaction.replace(R.id.viewPager, new MainMenu()).commit();

    }

    // responsible for creating the BottomNavigation
    private void configBottomNavigationView() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);

        // enable navigation
        enableNavigation(bottomNavigationView);
    }

    public void enableNavigation(BottomNavigationView view){
        view.setOnItemSelectedListener(item -> {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            int itemId = item.getItemId();
            if (itemId == R.id.menuFragment) {
                fragmentTransaction.replace(R.id.viewPager, new MenuFragment()).commit();
                return true;
            } else if (itemId == R.id.cartFragment) {
                fragmentTransaction.replace(R.id.viewPager, new CartFragment()).commit();
                return true;
            } else if (itemId == R.id.locationFragment) {
                fragmentTransaction.replace(R.id.viewPager, new LocationFragment()).commit();
                return true;
            } else if (itemId == R.id.profileFragment) {
                fragmentTransaction.replace(R.id.viewPager, new ProfileFragment()).commit();
                return true;
            }
            // if no fragment is committed, then return false
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

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