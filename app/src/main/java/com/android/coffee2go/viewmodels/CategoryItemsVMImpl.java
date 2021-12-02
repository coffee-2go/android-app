package com.android.coffee2go.viewmodels;

import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.repository.MenuItemsRepository;
import com.android.coffee2go.repository.TransactionRepository;

import java.util.List;

public class CategoryItemsVMImpl extends ViewModel implements CategoryItemsVM {
    private MenuItemsRepository repository;

    public CategoryItemsVMImpl() {
        repository = MenuItemsRepository.getInstance();
    }

    @Override
    public List<MenuItem> getCategoryItems(int position) {
        return repository.getCategoryItems(position);
    }

    @Override
    public void addOrderLine(OrderLine orderLine) {
        TransactionRepository.getInstance().addOrderLine(orderLine);
    }
}
