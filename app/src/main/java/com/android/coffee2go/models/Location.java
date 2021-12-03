package com.android.coffee2go.models;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private String shopName;
    private LatLng coordinates;
    private String address;

//    public Location(String shopName, LatLng coordinates, String address) {
//        this.shopName = shopName;
//        this.coordinates = coordinates;
//        this.address = address;
//    }

    public Location(String shopName, LatLng coordinates) {
        this.shopName = shopName;
        this.coordinates = coordinates;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "shopName='" + shopName + '\'' +
                ", coordinates=" + coordinates.latitude + ", " + coordinates.longitude +
                ", address='" + address + '\'' +
                '}';
    }
}
