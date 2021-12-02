package com.android.coffee2go.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.coffee2go.R;
import com.android.coffee2go.data.MenuItemsDAO;
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
    private final List<MenuItem> categories;
    private final List<MenuItem> allItems;

    private MutableLiveData<List<MenuItem>> itemsToShow;


    private MenuItemsRepository(){
//        bakeryMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Bakery");
//        breakfastMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Breakfast");
//        coldCoffeeMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Cold Coffee");
//        coldDrinkMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Cold Drinks");
//        hotCoffeeMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Hot Coffee");
//        hotDrinksMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Hot Drinks");
//        sandwichesMenuItems = MenuItemsDAO.getInstance().GetMenuItems("Sandwiches");

        categories = new ArrayList<>();
        categories.add(new MenuItem("Hot Coffees", R.drawable.hot_coffee));
        categories.add(new MenuItem("Cold Coffees",R.drawable.cold_coffee));
        categories.add(new MenuItem("Hot Drinks",R.drawable.hot_drinks));
        categories.add(new MenuItem("Cold Drinks",R.drawable.cold_drinks));
        categories.add(new MenuItem("Breakfast",R.drawable.breakfast));
        categories.add(new MenuItem("Sandwiches",R.drawable.sandwiches));
        categories.add(new MenuItem("Bakery",R.drawable.bakery));
        categories.add(new MenuItem("All Items",R.drawable.all_items));

        hotCoffeeMenuItems = new ArrayList<>();
        hotCoffeeMenuItems.add(new MenuItem("Cappuccino",R.drawable.cappuccino,35));
        hotCoffeeMenuItems.add(new MenuItem("Espresso",R.drawable.espresso,25));
        hotCoffeeMenuItems.add(new MenuItem("Caffe Americano",R.drawable.caffe_americano,40));
        hotCoffeeMenuItems.add(new MenuItem("Caffe Misto",R.drawable.caffe_misto,30));
        hotCoffeeMenuItems.add(new MenuItem("Caramel Macchiato",R.drawable.caramel_macchiato,45));
        hotCoffeeMenuItems.add(new MenuItem("Flat White",R.drawable.flat_white,30));
        hotCoffeeMenuItems.add(new MenuItem("Caffe Mocha",R.drawable.caffe_mocha,40));

        coldCoffeeMenuItems = new ArrayList<>();
        coldCoffeeMenuItems.add(new MenuItem("Irish Cream Coffee",R.drawable.irish_cream_coffee,40));
        coldCoffeeMenuItems.add(new MenuItem("Banana Milk Coffee",R.drawable.banana_milk_coffee,40));

        hotDrinksMenuItems = new ArrayList<>();
        hotDrinksMenuItems.add(new MenuItem("Hot Chocolate",R.drawable.hot_chocolate,20));

        coldDrinkMenuItems = new ArrayList<>();
        coldDrinkMenuItems.add(new MenuItem("Lemonade",R.drawable.limonade,15));

        breakfastMenuItems = new ArrayList<>();
        breakfastMenuItems.add(new MenuItem("Ham & Eggs",R.drawable.ham_eggs,30));

        sandwichesMenuItems = new ArrayList<>();
        sandwichesMenuItems.add(new MenuItem("Ham Sandwich",R.drawable.ham_sandwich,30));

        bakeryMenuItems = new ArrayList<>();
        bakeryMenuItems.add(new MenuItem("Croissant",R.drawable.croissants,15));

        allItems = new ArrayList<>();
        allItems.addAll(hotCoffeeMenuItems);
        allItems.addAll(coldCoffeeMenuItems);
        allItems.addAll(hotDrinksMenuItems);
        allItems.addAll(coldDrinkMenuItems);
        allItems.addAll(breakfastMenuItems);
        allItems.addAll(sandwichesMenuItems);
        allItems.addAll(bakeryMenuItems);
        itemsToShow = new MutableLiveData<>();
        itemsToShow.setValue(allItems);
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

    public LiveData<List<MenuItem>> getAllItems() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        menuItems.addAll(hotCoffeeMenuItems);
        menuItems.addAll(coldCoffeeMenuItems);
        menuItems.addAll(hotDrinksMenuItems);
        menuItems.addAll(coldDrinkMenuItems);
        menuItems.addAll(breakfastMenuItems);
        menuItems.addAll(sandwichesMenuItems);
        menuItems.addAll(bakeryMenuItems);

        itemsToShow.setValue(menuItems);

        return itemsToShow;
    }

    public List<MenuItem> getCategoryItems(int position) {
        switch (position){
            case 0:
            {
                itemsToShow.setValue(hotCoffeeMenuItems);
                return hotCoffeeMenuItems;
            }
            case 1:
            {
                itemsToShow.setValue(coldCoffeeMenuItems);
                return coldCoffeeMenuItems;
            }
            case 2:
            {
                itemsToShow.setValue(hotDrinksMenuItems);
                return hotDrinksMenuItems;
            }case 3:
            {
                itemsToShow.setValue(coldDrinkMenuItems);
                return coldDrinkMenuItems;
            }case 4:
            {
                itemsToShow.setValue(breakfastMenuItems);
                return breakfastMenuItems;
            }case 5:
            {
                itemsToShow.setValue(sandwichesMenuItems);
                return sandwichesMenuItems;
            }case 6:
            {
                itemsToShow.setValue(bakeryMenuItems);
                return bakeryMenuItems;
            }case 7:
            {
                itemsToShow.setValue(allItems);
                return allItems;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

    public MenuItem getItem(int position) {
        return itemsToShow.getValue().get(position);
    }

    public void filterItems(String trim) {
        List<MenuItem> items = new ArrayList<>();
        for (MenuItem m: getAllItems().getValue()) {
            if (!m.getName().toLowerCase().contains(trim.toLowerCase())){
                items.add(m);
            }
        }
        itemsToShow.getValue().removeAll(items);
    }
}
