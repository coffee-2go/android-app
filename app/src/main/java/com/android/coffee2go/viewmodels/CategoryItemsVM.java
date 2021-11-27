package com.android.coffee2go.viewmodels;

import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;

import java.util.ArrayList;

public interface CategoryItemsVM {
    ArrayList<MenuItem> getCategoryItems();
    void addOrderLine(OrderLine orderLine);
}
