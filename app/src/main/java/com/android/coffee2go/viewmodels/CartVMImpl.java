package com.android.coffee2go.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.persistence.TransactionRepository;

import java.util.List;

public class CartVMImpl extends ViewModel implements CartVM {

    @Override
    public void removeOrder(int adapterPosition) {
        TransactionRepository.getInstance().removeOrderLine(adapterPosition);
    }

    @Override
    public LiveData<List<OrderLine>> getTransactionOrderLines() {
        return TransactionRepository.getInstance().getTransactionOrderLines();
    }
}
