package com.android.coffee2go.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.view.adapters.CategoryMenuListAdapter;
import com.android.coffee2go.viewmodels.CategoryItemsVM;
import com.android.coffee2go.viewmodels.CategoryItemsVMImpl;
import com.android.coffee2go.viewmodels.OnListItemClickListener;

/**
 * @author Michal Pupák
 * **/
//TODO Attach bottom navigation to the activity, make an option for a customer to go back
//TODO Make a drop down with product details or create new activity with product's details
//TODO Make the activity display the list of items corresponding to activity which was clicked
public class CategoryItemsActivity extends AppCompatActivity implements OnListItemClickListener {
    RecyclerView categoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        // config toolbar
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Items");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryItems = findViewById(R.id.categoryItems);
        categoryItems.hasFixedSize();
        categoryItems.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        CategoryItemsVM categoryItemsVM = new ViewModelProvider(this).get(CategoryItemsVMImpl.class);

        CategoryMenuListAdapter menuListAdapter = new CategoryMenuListAdapter(categoryItemsVM,position, this);

        categoryItems.setAdapter(menuListAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
    }
}