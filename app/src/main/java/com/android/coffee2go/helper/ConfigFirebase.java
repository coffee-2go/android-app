package com.android.coffee2go.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class ConfigFirebase {
    private static FirebaseAuth mAuth;
    private static DatabaseReference myRef;
    private static FirebaseStorage storage;
    private static final String urlFirebaseDatabase = "https://coffee2go-baf44-default-rtdb.europe-west1.firebasedatabase.app/";


    // return instance of auth firebase
    public static FirebaseAuth getFirebaseAuth() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    // return instance of Realtime database
    public static DatabaseReference getDatabaseReference() {
        if (myRef == null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance(urlFirebaseDatabase);
            myRef = database.getReference();
        }
        return myRef;
    }

    // return instance of Storage
    public static FirebaseStorage getFirebaseStorage() {
        if (storage == null) {
            storage = FirebaseStorage.getInstance();
        }
        return storage;
    }
}
