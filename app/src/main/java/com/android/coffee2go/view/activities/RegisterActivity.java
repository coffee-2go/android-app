package com.android.coffee2go.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.coffee2go.R;
import com.android.coffee2go.models.Account;
import com.android.coffee2go.viewmodels.RegisterVM;
import com.android.coffee2go.viewmodels.RegisterVMImpl;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar progressBar;
    private Button buttonRegister;

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;

    //private FirebaseAuth mAuth;
    private RegisterVM registerVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = findViewById(R.id.registerButton);
        buttonRegister.setOnClickListener(this);

        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmailAddress);
        editPassword = findViewById(R.id.editPassword);

//        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        registerVM = new ViewModelProvider(this).get(RegisterVMImpl.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String username = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (username.isEmpty()){
            editUsername.setError("Username is required!");
            editUsername.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editPassword.setError("Password must be at least 6 characters long!");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        registerVM.AddAccount(new Account(username,email,password));
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()){
//                        Account account = new Account(username,email,password);
//                        FirebaseDatabase.getInstance().getReference("Users")
//                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
//                                .setValue(account).addOnCompleteListener(task1 -> {
//                                    if (task1.isSuccessful()){
//                                        Toast.makeText(getApplicationContext(),"Registration was successful",
//                                                Toast.LENGTH_LONG).show();
//                                    }else{
//                                        Toast.makeText(getApplicationContext(),"Error occurred",
//                                                Toast.LENGTH_LONG).show();
//                                    }
//                                    progressBar.setVisibility(View.GONE);
//                                });
//                    }else{
//                        Toast.makeText(getApplicationContext(),"Error occurred",
//                                Toast.LENGTH_LONG).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
//                });

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}