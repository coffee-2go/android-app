package com.android.coffee2go.models;

public class MenuItem{
    private String name;
    private int iconId;
    private double price;
    private String path;

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", iconId=" + iconId +
                ", price=" + price +
                ", path='" + path + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MenuItem() {
    }

    public MenuItem(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    public MenuItem(String name, int iconId, double unitPrice) {
        this.name = name;
        this.iconId = iconId;
        this.price = unitPrice;
    }

    public double getTotal(){
        return price;
    }

    public int getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}