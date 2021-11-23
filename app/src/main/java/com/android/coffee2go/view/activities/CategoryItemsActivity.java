package com.android.coffee2go.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.viewmodels.adapters.CategoryMenuListAdapter;
import com.android.coffee2go.viewmodels.OnListItemClickListener;

import java.util.ArrayList;

public class CategoryItemsActivity extends AppCompatActivity implements OnListItemClickListener {
    RecyclerView categoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        categoryItems = findViewById(R.id.categoryItems);
        categoryItems.hasFixedSize();
        categoryItems.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Cappuccino",R.drawable.cappuccino));
        menuItems.add(new MenuItem("Espresso",R.drawable.espresso));
        menuItems.add(new MenuItem("Caffe Americano",R.drawable.caffe_americano));
        menuItems.add(new MenuItem("Caffe Misto",R.drawable.caffe_misto));
        menuItems.add(new MenuItem("Caramel Macchiato",R.drawable.caramel_macchiato));
        menuItems.add(new MenuItem("Flat White",R.drawable.flat_white));
        menuItems.add(new MenuItem("Cappuccino",R.drawable.caffe_mocha));

        CategoryMenuListAdapter menuListAdapter = new CategoryMenuListAdapter(menuItems,this);

        categoryItems.setAdapter(menuListAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
    }
}