package com.android.coffee2go.viewmodels;

import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.Location;
import com.android.coffee2go.repository.LocationRepository;

import java.util.ArrayList;

public class LocationVMImpl extends ViewModel {
    private static LocationVMImpl instance;
    private LocationRepository locationRepository;

//    public static synchronized LocationVMImpl getInstance(){
//        if (instance == null){
//            instance = new LocationVMImpl();
//        }
//        return instance;
//    }

    public void setStore(String title) {
        //
    }

    public ArrayList<Location> getLocations(){
        locationRepository = LocationRepository.getInstance();
        return locationRepository.getLocations();
    }
}