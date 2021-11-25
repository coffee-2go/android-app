package com.android.coffee2go.models;

import java.util.ArrayList;

/**
 * @author Michal Pupák
 * **/
public class MenuItem{
    private String mName;
    private int mIconId;
    private double mUnitPrice;
    private ArrayList<Accessory> accessories;

    public MenuItem(String mName, int mIconId) {
        this.mName = mName;
        this.mIconId = mIconId;
        accessories = new ArrayList<>();
    }

    public double getTotal(){
        double accessoryTotal = 0;
        for (Accessory u: accessories) {
            accessoryTotal += u.getUnitPrice();
        }
        return mUnitPrice + accessoryTotal;
    }

    public int getIconId() {
        return mIconId;
    }

    public String getName() {
        return mName;
    }

    public double getUnitPrice() {
        return mUnitPrice;
    }

    public void setUnitPrice(double mUnitPrice) {
        this.mUnitPrice = mUnitPrice;
    }

    public ArrayList<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(ArrayList<Accessory> accessories) {
        this.accessories = accessories;
    }
}