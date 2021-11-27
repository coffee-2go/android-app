package com.android.coffee2go.viewmodels;

import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.persistence.TransactionRepository;

import java.util.ArrayList;

public class CategoryItemsVMImpl extends ViewModel implements CategoryItemsVM {

    @Override
    public ArrayList<MenuItem> getCategoryItems() {
        return null;
    }

    @Override
    public void addOrderLine(OrderLine orderLine) {
        TransactionRepository.getInstance().addOrderLine(orderLine);
    }
}
