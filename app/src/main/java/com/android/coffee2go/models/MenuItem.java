package com.android.coffee2go.models;

public class MenuItem{
    private String mName;
    private int mIconId;

    public MenuItem(String mName, int mIconId) {
        this.mName = mName;
        this.mIconId = mIconId;
    }

    public int getIconId() {
        return mIconId;
    }

    public String getName() {
        return mName;
    }
}