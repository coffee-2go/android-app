package com.android.coffee2go.view.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.android.coffee2go.R;
import com.android.coffee2go.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment == null) throw new AssertionError();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //Initialize the map
        mMap = googleMap;

        // A few coffee shop coordinates
        LatLng seveneleven = new LatLng(55.862954, 9.836691);
        LatLng cafeoxygen = new LatLng(55.862946, 9.848320);
        LatLng cafeovivaldi = new LatLng(55.862195, 9.847103);
        LatLng cafesocialistik = new LatLng(55.863829, 9.848038);
        LatLng cafehimebla = new LatLng(55.851925, 9.830076);

        //Adding the coffee shop coordinates to the map as markers
        mMap.addMarker(new MarkerOptions().position(seveneleven).title("Marker in 7/11 horsens train station"));
        mMap.addMarker(new MarkerOptions().position(cafeoxygen).title("Marker in cafe oxygen"));
        mMap.addMarker(new MarkerOptions().position(cafeovivaldi).title("cafe vivaldi"));
        mMap.addMarker(new MarkerOptions().position(cafesocialistik).title("socialist coffee club"));
        mMap.addMarker(new MarkerOptions().position(cafehimebla).title("Mcafe imebla"));

        //Zoom the map to orsens
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafeoxygen, 15f));
    }
}