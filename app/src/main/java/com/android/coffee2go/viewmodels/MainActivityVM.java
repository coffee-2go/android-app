package com.android.coffee2go.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.coffee2go.persistence.AccountRepository;
//import androidx.lifecycle.LiveData;
//
//import com.google.firebase.auth.FirebaseUser;

public class MainActivityVM extends AndroidViewModel {

    private final AccountRepository accountRepository;

    public MainActivityVM(@NonNull Application application) {
        super(application);
        accountRepository = AccountRepository.getInstance();
    }

    public void init() {}
}
