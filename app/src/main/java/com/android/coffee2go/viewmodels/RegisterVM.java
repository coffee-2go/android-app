package com.android.coffee2go.viewmodels;

import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.Account;
import com.android.coffee2go.repository.ProfileRepository;

public class RegisterVM extends ViewModel {

    private final ProfileRepository repository;

    public RegisterVM() {
        repository = ProfileRepository.getInstance();
    }

    public void save(Account account) {
        repository.save(account);
    }

}
