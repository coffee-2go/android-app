package com.android.coffee2go.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.coffee2go.R;
import com.android.coffee2go.viewmodels.MainActivityVM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MainActivityVM viewModel;
    private Button buttonRegister;
    private Button buttonLogin;
    private ProgressBar progressBar;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityVM.class);
        viewModel.init();
        setContentView(R.layout.activity_main);

        buttonRegister = findViewById(R.id.registerButton);
        buttonRegister.setOnClickListener(this);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        //mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
            {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.buttonLogin:
            {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}