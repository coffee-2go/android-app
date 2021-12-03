package com.android.coffee2go.data;

import com.android.coffee2go.helper.ConfigFirebase;
import com.google.firebase.storage.FirebaseStorage;

public class StorageDAO {

    private static StorageDAO instance;
    private FirebaseStorage storageRef;

    private StorageDAO() {
        storageRef = ConfigFirebase.getFirebaseStorage();
    }

    public static StorageDAO getInstance() {
        if (instance == null) {
            instance = new StorageDAO();
        }
        return instance;
    }
}
