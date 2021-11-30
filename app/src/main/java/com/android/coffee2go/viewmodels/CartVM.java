package com.android.coffee2go.viewmodels;

import androidx.lifecycle.LiveData;

import com.android.coffee2go.models.OrderLine;

import java.util.List;

public interface CartVM {

    void removeOrder(int adapterPosition);

    LiveData<List<OrderLine>> getTransactionOrderLines();

    double getTransactionTotal();
}
