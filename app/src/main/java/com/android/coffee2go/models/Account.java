package com.android.coffee2go.models;

import com.android.coffee2go.helper.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private String id;
    private String username;
    private String email;
    private String password;
    private String urlPicture;

    public Account() {
    }

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void save() {
        DatabaseReference firebaseRef = ConfigFirebase.getDatabaseReference();
        DatabaseReference accountRef = firebaseRef.child("accounts").child(getId());
        accountRef.setValue(this);
    }

    public void update() {
        // should do by the id

        DatabaseReference firebaseRef = ConfigFirebase.getDatabaseReference();
        DatabaseReference accountRef = firebaseRef
                .child("accounts")
                .child( getId() );

        Map<String, Object> accountData = convertToMap();
        accountRef.updateChildren( accountData );
    }

    public Map<String, Object> convertToMap() {
        HashMap<String, Object> accountMap = new HashMap<>();
        accountMap.put("email", getEmail());
        accountMap.put("username", getUsername());
        accountMap.put("id", getId());
        accountMap.put("urlPicture", getUrlPicture());

        return accountMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // @Exclude so it doesn't save the password in the realtime-database
    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", urlPicture='" + urlPicture + '\'' +
                '}';
    }
}
