package com.android.coffee2go.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.persistence.MenuItemsRepository;

import java.util.List;

public class MenuVM extends ViewModel {

    private MenuItemsRepository repository;

    public MenuVM() {
        repository = MenuItemsRepository.getInstance();
    }

    public List<MenuItem> getCategories(){
        return repository.getCategories();
    }
}
