package com.android.coffee2go.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.coffee2go.repository.AccountRepository;
//import androidx.lifecycle.LiveData;
//
//import com.google.firebase.auth.FirebaseUser;

public class LoginActivityVM extends AndroidViewModel {

    private final AccountRepository accountRepository;

    public LoginActivityVM(@NonNull Application application) {
        super(application);
        accountRepository = AccountRepository.getInstance();
    }

    public void init() {}
}
