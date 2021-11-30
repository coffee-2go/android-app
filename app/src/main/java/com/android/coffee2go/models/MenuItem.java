package com.android.coffee2go.models;

import java.util.ArrayList;

public class MenuItem{
    private String name;
    private int iconId;
    private double unitPrice;
    private ArrayList<Accessory> accessories;

    public MenuItem(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
        accessories = new ArrayList<>();
    }

    public MenuItem(String name, int iconId, double unitPrice) {
        this.name = name;
        this.iconId = iconId;
        this.unitPrice = unitPrice;
    }

    public double getTotal(){
        double accessoryTotal = 0;
//        for (Accessory u: accessories) {
//            accessoryTotal += u.getUnitPrice();
//        }
        return unitPrice + accessoryTotal;
    }

    public int getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ArrayList<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(ArrayList<Accessory> accessories) {
        this.accessories = accessories;
    }
}