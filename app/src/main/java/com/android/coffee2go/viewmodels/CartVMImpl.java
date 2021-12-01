package com.android.coffee2go.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.persistence.TransactionRepository;
import java.util.List;

public class CartVMImpl extends ViewModel implements CartVM {
    private TransactionRepository repository;

    public CartVMImpl() {
        repository = TransactionRepository.getInstance();
    }

    @Override
    public void removeOrder(int adapterPosition) {
        repository.removeOrderLine(adapterPosition);
    }

    @Override
    public LiveData<List<OrderLine>> getTransactionOrderLines() {
        return repository.getTransactionOrderLines();
    }

    @Override
    public LiveData<Double> getTransactionTotal() {
        return repository.getTransactionTotal();
    }

    @Override
    public void changeQuantity(int position, int newQuantity) {
        repository.changeQuantity(position,newQuantity);
    }
}
