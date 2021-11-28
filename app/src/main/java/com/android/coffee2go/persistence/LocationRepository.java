package com.android.coffee2go.persistence;


import com.android.coffee2go.data.AccountDAO;
import com.android.coffee2go.data.LocationDAO;
import com.android.coffee2go.models.Location;

/**
 * @author Alex
 */

public class LocationRepository {
    private static LocationRepository instance;
    private final LocationDAO locationDAO;

    private LocationRepository() {
        locationDAO = LocationDAO.getInstance();
    }

    public static synchronized LocationRepository getInstance() {
        if(instance == null)
            instance = new LocationRepository();
        return instance;
    }

    public void AddAccount(Location location) {
        locationDAO.addLocation(location);
    }
}
