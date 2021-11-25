package com.android.coffee2go.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.coffee2go.R;
import com.android.coffee2go.helper.FirebaseConfig;
import com.android.coffee2go.models.Account;
import com.android.coffee2go.viewmodels.RegisterVM;
import com.android.coffee2go.viewmodels.RegisterVMImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonRegister;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private DatabaseReference db;
    //private RegisterVM registerVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();

        // check registration and register
        progressBar.setVisibility(View.GONE);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = editUsername.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (!username.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!password.isEmpty()) {
                            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                if (password.length() > 6) {

                                    Account account = new Account();
                                    account.setUsername(username);
                                    account.setEmail(email);
                                    account.setPassword(password);
                                    register(account);

                                } else {
                                    editPassword.setError("Password must be at least 6 characters long!");
                                    editPassword.requestFocus();
                                }
                            } else {
                                editEmail.setError("Please provide valid email!");
                                editEmail.requestFocus();
                            }
                        } else {
                            editPassword.setError("Password is required!");
                            editPassword.requestFocus();
                        }
                    } else {
                        editEmail.setError("Email is required!");
                        editEmail.requestFocus();
                    }
                } else {
                    editUsername.setError("Username is required!");
                    editUsername.requestFocus();
                }
            }
        });

        //registerVM = new ViewModelProvider(this).get(RegisterVMImpl.class);
    }

    private void register(Account account) {

        progressBar.setVisibility(View.VISIBLE);
        auth = FirebaseConfig.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(
                account.getEmail(),
                account.getPassword()
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if ( task.isSuccessful() ) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();

                        } else {
                            progressBar.setVisibility(View.GONE);
                            String errorException;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                errorException = "Please type a stronger password!";
                            } catch (FirebaseAuthUserCollisionException e) {
                                errorException = "This account has been already registered!";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                errorException = "Please type a valid email!";
                            } catch (Exception e) {
                                errorException = "when registering a account: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(RegisterActivity.this, "Error: " + errorException,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void initComponents() {
        editUsername = findViewById(R.id.register_editUsername);
        editEmail = findViewById(R.id.register_editEmailAddress);
        editPassword = findViewById(R.id.register_editPassword);
        buttonRegister = findViewById(R.id.register_registerButton);
        progressBar = findViewById(R.id.register_progressBar);

        editUsername.requestFocus();
    }
}