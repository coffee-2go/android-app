package com.android.coffee2go.persistence;

import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepository {
    private static MenuItemsRepository instance;
    private List<MenuItem> menuItems;
    private List<MenuItem> categories;


    private MenuItemsRepository(){
        categories = new ArrayList<>();
        categories.add(new MenuItem("Hot Coffees", R.drawable.hot_coffee));
        categories.add(new MenuItem("Cold Coffees",R.drawable.cold_coffee));
        categories.add(new MenuItem("Hot Drinks",R.drawable.hot_drinks));
        categories.add(new MenuItem("Cold Drinks",R.drawable.cold_drinks));
        categories.add(new MenuItem("Breakfast",R.drawable.breakfast));
        categories.add(new MenuItem("Sandwiches",R.drawable.sandwiches));
        categories.add(new MenuItem("Bakery",R.drawable.bakery));
        categories.add(new MenuItem("Snacks",R.drawable.snacks));

        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Cappuccino",R.drawable.cappuccino));
        menuItems.add(new MenuItem("Espresso",R.drawable.espresso));
        menuItems.add(new MenuItem("Caffe Americano",R.drawable.caffe_americano));
        menuItems.add(new MenuItem("Caffe Misto",R.drawable.caffe_misto));
        menuItems.add(new MenuItem("Caramel Macchiato",R.drawable.caramel_macchiato));
        menuItems.add(new MenuItem("Flat White",R.drawable.flat_white));
        menuItems.add(new MenuItem("Cappuccino",R.drawable.caffe_mocha));
    }

    public static synchronized MenuItemsRepository getInstance(){
        if (instance == null){
            instance = new MenuItemsRepository();
        }
        return instance;
    }

    public List<MenuItem> getCategories() {
        return categories;
    }
}
