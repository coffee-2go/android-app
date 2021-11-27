package com.android.coffee2go.persistence;

import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepository {
    private static MenuItemsRepository instance;
    private final List<MenuItem> hotCoffeeMenuItems;
    private final List<MenuItem> coldCoffeeMenuItems;
    private final List<MenuItem> hotDrinksMenuItems;
    private final List<MenuItem> coldDrinkMenuItems;
    private final List<MenuItem> breakfastMenuItems;
    private final List<MenuItem> sandwichesMenuItems;
    private final List<MenuItem> bakeryMenuItems;
    private final List<MenuItem> snacksMenuItems;
    private final List<MenuItem> categories;


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

        hotCoffeeMenuItems = new ArrayList<>();
        hotCoffeeMenuItems.add(new MenuItem("Cappuccino",R.drawable.cappuccino));
        hotCoffeeMenuItems.add(new MenuItem("Espresso",R.drawable.espresso));
        hotCoffeeMenuItems.add(new MenuItem("Caffe Americano",R.drawable.caffe_americano));
        hotCoffeeMenuItems.add(new MenuItem("Caffe Misto",R.drawable.caffe_misto));
        hotCoffeeMenuItems.add(new MenuItem("Caramel Macchiato",R.drawable.caramel_macchiato));
        hotCoffeeMenuItems.add(new MenuItem("Flat White",R.drawable.flat_white));
        hotCoffeeMenuItems.add(new MenuItem("Cappuccino",R.drawable.caffe_mocha));

        coldCoffeeMenuItems = new ArrayList<>();
        hotDrinksMenuItems = new ArrayList<>();
        coldDrinkMenuItems = new ArrayList<>();
        breakfastMenuItems = new ArrayList<>();
        sandwichesMenuItems = new ArrayList<>();
        bakeryMenuItems = new ArrayList<>();
        snacksMenuItems = new ArrayList<>();
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


    public List<MenuItem> getCategoryItems(int position) {
        switch (position){
            case 0:
            {
                return hotCoffeeMenuItems;
            }
            case 1:
            {
                return coldCoffeeMenuItems;
            }
            case 2:
            {
                return hotDrinksMenuItems;
            }case 3:
            {
                return coldDrinkMenuItems;
            }case 4:
            {
                return breakfastMenuItems;
            }case 5:
            {
                return sandwichesMenuItems;
            }case 6:
            {
                return bakeryMenuItems;
            }case 7:
            {
                return snacksMenuItems;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
