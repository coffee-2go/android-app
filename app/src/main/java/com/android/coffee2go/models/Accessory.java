package com.android.coffee2go.models;

/**
 * @author Michal Pupák
 * **/
public class Accessory {
    private String name;
    private double unitPrice;

    public Accessory(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
