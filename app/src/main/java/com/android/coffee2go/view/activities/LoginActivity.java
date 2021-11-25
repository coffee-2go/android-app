package com.android.coffee2go.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.coffee2go.R;
import com.android.coffee2go.helper.FirebaseConfig;
import com.android.coffee2go.models.Account;
import com.android.coffee2go.viewmodels.LoginActivityVM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private ProgressBar progressBar;

    private Account account;
    private FirebaseAuth auth;

    private LoginActivityVM viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //verifyLoggedAccount();
        initComponents();

        viewModel = new ViewModelProvider(this).get(LoginActivityVM.class);
        viewModel.init();

        // register
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        // login
        progressBar.setVisibility(View.GONE);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (!email.isEmpty()) {
                    if (!password.isEmpty()) {

                        account = new Account();
                        account.setEmail(email);
                        account.setPassword(password);
                        validateLogin(account);

                    } else {
                        editPassword.setError("Please provide a password!");
                        editPassword.requestFocus();
                    }
                } else {
                    editEmail.setError("Please provide an email!");
                    editEmail.requestFocus();
                }
            }
        });
    }

    private void validateLogin(Account account) {
        progressBar.setVisibility(View.VISIBLE);
        auth = FirebaseConfig.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
                account.getEmail(),
                account.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ) {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Error when trying to login", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void verifyLoggedAccount() {
        auth = FirebaseConfig.getFirebaseAuth();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            finish();
        }
    }

    public void initComponents() {
        editEmail = findViewById(R.id.login_editEmailAddress);
        editPassword = findViewById(R.id.login_editPassword);
        buttonLogin = findViewById(R.id.login_buttonLogin);
        buttonRegister = findViewById(R.id.login_registerButton);
        progressBar = findViewById(R.id.login_progressBar);

        editEmail.requestFocus();
    }
}