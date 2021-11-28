package com.android.coffee2go.viewmodels;

import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;

import java.util.ArrayList;
import java.util.List;

public interface CategoryItemsVM {
    List<MenuItem> getCategoryItems(int position);
    void addOrderLine(OrderLine orderLine);
}
