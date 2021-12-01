package com.android.coffee2go.helper;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.coffee2go.models.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AccountFirebase {

    public static FirebaseUser getCurrentAccount() {
        FirebaseAuth account = ConfigFirebase.getFirebaseAuth();
        return account.getCurrentUser();
    }

    public static String getAccountUid() {
        return getCurrentAccount().getUid();
    }

    public static void updateUsername(String username) {
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


    public static void updatePictureAccount(Uri url) {
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
                    Log.d("Profile", "Error when trying to update username.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // return account with data from firebase realtime-database
    public static Account getDataLoggedAccount() {

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
}
