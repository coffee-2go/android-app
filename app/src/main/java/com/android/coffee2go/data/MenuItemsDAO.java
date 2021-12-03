package com.android.coffee2go.data;

import android.util.Log;
import androidx.annotation.NonNull;
import com.android.coffee2go.helper.ConfigFirebase;
import com.android.coffee2go.models.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsDAO {
    private DatabaseReference reference;
    private static MenuItemsDAO instance;

    private MenuItemsDAO(){
        reference = ConfigFirebase.getDatabaseReference();
        reference = reference.child("categories");
    }

    public static MenuItemsDAO getInstance(){
        if (instance == null){
            instance = new MenuItemsDAO();
        }
        return instance;
    }

    public List<MenuItem> GetMenuItems(String category){
        List<MenuItem> items = new ArrayList<>();
        reference.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    MenuItem item = child.getValue(MenuItem.class);
                    items.add(item);
                    Log.i("MENU ITEM DAO","MENU ITEM: "+item.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("ACCOUNT DAO", "Failed to read value.", error.toException());
            }
        });
        return items;
    }

}
