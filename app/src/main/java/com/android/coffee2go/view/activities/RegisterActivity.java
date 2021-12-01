package com.android.coffee2go.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.coffee2go.R;
import com.android.coffee2go.helper.AccountFirebase;
import com.android.coffee2go.helper.ConfigFirebase;
import com.android.coffee2go.models.Account;
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

    private FirebaseAuth mAuth;
    //private RegisterVM registerVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();

        // check registration and register
        progressBar.setVisibility(View.GONE);
        buttonRegister.setOnClickListener(view -> {

            String username = editUsername.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (!username.isEmpty()) {
                if (!email.isEmpty()) {
                    if (!password.isEmpty()) {
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            if (password.length() >= 6) {

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
        });

        //registerVM = new ViewModelProvider(this).get(RegisterVMImpl.class);
    }

    private void register(Account account) {

        progressBar.setVisibility(View.VISIBLE);
        mAuth = ConfigFirebase.getFirebaseAuth();
        mAuth.createUserWithEmailAndPassword(
                account.getEmail(),
                account.getPassword()
        ).addOnCompleteListener(
                this,
                task -> {

                    if ( task.isSuccessful() ) {

                        try {
                            progressBar.setVisibility(View.GONE);

                            // Save account in the realtime-database-firebase
                            String idAccount = task.getResult().getUser().getUid();
                            account.setId(idAccount);
                            account.save();

                            // Save username in Auth-firebase
                            AccountFirebase.updateUsername(account.getUsername());

                            Toast.makeText(RegisterActivity.this,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

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