package com.android.coffee2go.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.coffee2go.R;
import com.android.coffee2go.models.Location;
import com.android.coffee2go.view.activities.ShopConfirmationActivity;
import com.android.coffee2go.viewmodels.LocationVMImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class LocationFragment extends Fragment {
    LocationVMImpl locationVM;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        private GoogleMap mMap;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            startVM();
            ArrayList<Location> shopLocations = locationVM.getLocations();

            this.mMap = googleMap;
            if (!shopLocations.isEmpty()){
                for (Location location: shopLocations) {
                    System.out.println(location.getCoordinates().toString());
                    System.out.println(location.getShopName());
                    mMap.addMarker(new MarkerOptions().position(location.getCoordinates()).title(location.getShopName()));
                }
            }

            // Set a listener for marker click.
            //mMap.setOnMarkerClickListener(this);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.862946, 9.848320), 15f));

            //mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    String markerTitle = marker.getTitle();

                    Intent intent = new Intent(getActivity(), ShopConfirmationActivity.class);
                    intent.putExtra("title", markerTitle);
                    startActivity(intent);

                    return true;
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void startVM(){
        locationVM = new ViewModelProvider(this).get(LocationVMImpl.class);
    }

}