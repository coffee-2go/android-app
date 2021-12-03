package com.android.coffee2go.viewmodels;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.Account;
import com.android.coffee2go.repository.ProfileRepository;
import com.google.firebase.auth.FirebaseUser;

public class ProfileVM extends ViewModel {

    private final ProfileRepository profileRepository;

    public ProfileVM() {
        profileRepository = ProfileRepository.getInstance();
    }

    public LiveData<Account> getDataLoggedAccount() {
        //LiveData<Account> dataLoggedAccount = profileRepository.getDataLoggedAccount();
        //Log.i("PROFILE VM","GET LOGGED ACCOUNT "+dataLoggedAccount.toString());
        // return dataLoggedAccount;
        return profileRepository.getDataLoggedAccount();
    }

    public Account getAuthLoggedAccount() {
        return profileRepository.getAuthLoggedAccount();
    }

    public String getAuthAccountUid() {
        return profileRepository.getAuthAccountUid();
    }

    public void updatePictureAccount(Uri url) {
        profileRepository.updatePictureAccount(url);
    }

    public void updateAccount(Account account) {
        profileRepository.updateAccount(account);
    }

    public void updateUsername(String updatedUsername) {
        profileRepository.updateUsername(updatedUsername);
    }

    public FirebaseUser getCurrentAccount() {
        return profileRepository.getCurrentAccount();
    }
}
