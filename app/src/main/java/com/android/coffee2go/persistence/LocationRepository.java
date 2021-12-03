package com.android.coffee2go.persistence;


import com.android.coffee2go.models.Location;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class LocationRepository {
    private static LocationRepository instance;
    private final ArrayList<Location> locations;

    private LocationRepository(){
        locations = new ArrayList<>();
        locations.add(new Location("Seven eleven", new LatLng(55.862954, 9.836691)));
        locations.add(new Location("Cafe Oxygen", new LatLng(55.862946, 9.848320)));
        locations.add(new Location("Cafe Vivaldi", new LatLng(55.862195, 9.847103)));
        locations.add(new Location("Socialist Coffee Club", new LatLng(55.863829, 9.848038)));
        locations.add(new Location("Cafe Imebla", new LatLng(55.851925, 9.830076)));
        locations.add(new Location("Café Lorentzen", new LatLng(55.873807, 9.836652)));
        locations.add(new Location("VINöL Gastro - Rock - Bar", new LatLng(55.861745, 9.852422)));
        locations.add(new Location("Café Noisette", new LatLng(55.862950, 9.846272)));
        locations.add(new Location("The Board Game Café", new LatLng(55.863601, 9.844228)));
    }

    public static synchronized LocationRepository getInstance(){
        if (instance == null){
            instance = new LocationRepository();
        }
        return instance;
    }

    public ArrayList<Location> getLocations(){
        return locations;
    }



}
