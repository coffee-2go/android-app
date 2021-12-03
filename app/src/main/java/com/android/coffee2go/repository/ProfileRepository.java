package com.android.coffee2go.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import com.android.coffee2go.data.AccountDAO;
import com.android.coffee2go.data.FirebaseAuthDAO;
import com.android.coffee2go.data.StorageDAO;
import com.android.coffee2go.models.Account;
import com.google.firebase.auth.FirebaseUser;

public class ProfileRepository {

    private static ProfileRepository instance;

    private final FirebaseAuthDAO firebaseAuthDAO;
    private final AccountDAO accountDAO;
    private final StorageDAO storageDAO;

    public ProfileRepository() {
        accountDAO = AccountDAO.getInstance();
        storageDAO = StorageDAO.getInstance();
        firebaseAuthDAO = FirebaseAuthDAO.getInstance();
    }

    public static ProfileRepository getInstance() {
        if (instance == null) {
            instance = new ProfileRepository();
        }
        return instance;
    }

    // return account data from mAuth loggedAccount
    public LiveData<Account> getDataLoggedAccount() {
        return accountDAO.getDataLoggedAccount();
    }

    public void save(Account account) {
        // save account in realtime firebase
        accountDAO.save(account);

        // save username in auth firebase
        firebaseAuthDAO.updateUsername(account.getUsername());
    }

    public Account getAuthLoggedAccount() {
        return firebaseAuthDAO.getAuthLoggedAccount();
    }

    public String getAuthAccountUid() {
        return firebaseAuthDAO.getAccountUid();
    }

    public void updatePictureAccount(Uri url) {
        firebaseAuthDAO.updatePictureAccount(url);
    }

    public void updateUsername(String updatedUsername) {
        firebaseAuthDAO.updateUsername(updatedUsername);
    }

    public void updateAccount(Account account) {
        accountDAO.update(account);
    }

    public FirebaseUser getCurrentAccount() {
        return firebaseAuthDAO.getCurrentAccount();
    }
}
