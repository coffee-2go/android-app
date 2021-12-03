package com.android.coffee2go.data;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.coffee2go.helper.ConfigFirebase;

import com.android.coffee2go.models.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseAuthDAO {

    private static FirebaseAuthDAO instance;
    private FirebaseAuth firebaseRef;

    private FirebaseAuthDAO() {
        firebaseRef = ConfigFirebase.getFirebaseAuth();
    }

    public static FirebaseAuthDAO getInstance() {
        if (instance == null) {
            instance = new FirebaseAuthDAO();
        }
        return instance;
    }


    // current account
    public FirebaseUser getCurrentAccount() {
        FirebaseAuth account = ConfigFirebase.getFirebaseAuth();
        return account.getCurrentUser();
    }


    // account id
    public String getAccountUid() {
        return firebaseRef.getUid();
    }

    public Uri getAccountPicture() {
        FirebaseUser accountProfile = getCurrentAccount();
        return accountProfile.getPhotoUrl();
    }

    public Account getAuthLoggedAccount() {

        FirebaseUser firebaseUser = getCurrentAccount();

        Account account = new Account();
        account.setEmail(firebaseUser.getEmail() );
        account.setUsername(firebaseUser.getDisplayName());
        account.setId(firebaseUser.getUid());

        if (firebaseUser.getPhotoUrl() == null) {
            account.setUrlPicture("");
        } else {
            account.setUrlPicture(firebaseUser.getPhotoUrl().toString());
        }

        return account;
    }


    public void updateUsername(String username) {
        try {
            // Account logged in the App
            FirebaseUser loggedAccount = getCurrentAccount();

            // Config object to edit profile
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(username)
                    .build();

            loggedAccount.updateProfile(profile).addOnCompleteListener(task -> {
                if( !task.isSuccessful() ) {
                    Log.d("Profile", "Error when trying to update username.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updatePictureAccount(Uri url) {
        try {
            // Account logged in the App
            FirebaseUser loggedAccount = getCurrentAccount();

            // Config object to edit profile
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setPhotoUri( url )
                    .build();

            loggedAccount.updateProfile(profile).addOnCompleteListener(task -> {
                if( !task.isSuccessful() ) {
                    Log.d("Profile", "Error when trying to update profile picture.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
