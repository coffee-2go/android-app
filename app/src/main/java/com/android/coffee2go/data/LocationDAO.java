package com.android.coffee2go.data;

import com.android.coffee2go.models.Account;
import com.android.coffee2go.models.Location;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Alex
 */

public class LocationDAO {
    private DatabaseReference reference;
    private static LocationDAO instance;

    public LocationDAO() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://coffee2go-baf44-default-rtdb.europe-west1.firebasedatabase.app");
        reference = database.getReference(Account.class.getSimpleName());
    }

    public static LocationDAO getInstance() {
        if (instance == null){
            instance = new LocationDAO();
        }
        return instance;
    }

    public Task<Void> addLocation(Location location){
        return reference.push().setValue(location);
    }


}
