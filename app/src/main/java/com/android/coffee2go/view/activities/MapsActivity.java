package com.example.googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.android.coffee2go.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapsBinding;

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
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng seveneleven = new LatLng(55.862954, 9.836691);
        LatLng cafeoxygen = new LatLng(55.862946, 9.848320);
        LatLng cafeovivaldi = new LatLng(55.862195, 9.847103);
        LatLng cafesocialistik = new LatLng(55.863829, 9.848038);
        LatLng cafehimebla = new LatLng(55.851925, 9.830076);
        mMap.addMarker(new MarkerOptions().position(seveneleven).title("Marker in 7/11 horsens train station"));
        mMap.addMarker(new MarkerOptions().position(cafeoxygen).title("Marker in cafe oxygen"));
        mMap.addMarker(new MarkerOptions().position(cafeovivaldi).title("cafe vivaldi"));
        mMap.addMarker(new MarkerOptions().position(cafesocialistik).title("socialist coffee club"));
        mMap.addMarker(new MarkerOptions().position(cafehimebla).title("Mcafe imebla"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafeoxygen, 15f));
    }
}