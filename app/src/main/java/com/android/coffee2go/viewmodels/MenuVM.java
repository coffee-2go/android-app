package com.android.coffee2go.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.persistence.MenuItemsRepository;
import com.android.coffee2go.persistence.TransactionRepository;

import java.util.List;

public class MenuVM extends ViewModel {

    private MenuItemsRepository repository;
    private TransactionRepository transactionRepository;

    public MenuVM() {
        repository = MenuItemsRepository.getInstance();
        transactionRepository = TransactionRepository.getInstance();
    }

    public List<MenuItem> getCategories(){
        return repository.getCategories();
    }

    public LiveData<List<MenuItem>> getAllItems() {
        return repository.getAllItems();
    }


    public List<MenuItem> getCategoryItems(int position) {
        return repository.getCategoryItems(position);
    }

    public MenuItem getItem(int position) {
        return repository.getItem(position);
    }

    public void addOrderLine(OrderLine orderLine) {
        transactionRepository.addOrderLine(orderLine);
    }

    public void filterItems(String trim) {
        repository.filterItems(trim);
    }
}
