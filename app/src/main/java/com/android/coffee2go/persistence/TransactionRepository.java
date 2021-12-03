package com.android.coffee2go.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.models.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Pupák
 * **/
public class TransactionRepository {
    private static TransactionRepository instance;
    private Transaction currentTransaction;
    private MutableLiveData<List<OrderLine>> transactionOrderLines;

    private TransactionRepository(){
        currentTransaction = new Transaction();
        transactionOrderLines = new MutableLiveData<>();
        List<OrderLine> orderLines = new ArrayList<>();
        transactionOrderLines.setValue(orderLines);
    }

    public LiveData<List<OrderLine>> getTransactionOrderLines() {
        return transactionOrderLines;
    }

    public void addOrderLine(OrderLine orderLine) {
        List<OrderLine> orderLines = transactionOrderLines.getValue();
        orderLines.add(orderLine);
        transactionOrderLines.setValue(orderLines);
        System.out.println("TRANSACTION REPOSITORY: ORDER LINE ADDED");;
    }

    public static synchronized TransactionRepository getInstance(){
        if (instance == null){
            instance = new TransactionRepository();
        }
        return instance;
    }

    public void removeOrderLine(int adapterPosition) {
        List<OrderLine> orderLines = transactionOrderLines.getValue();
        if (orderLines != null) {
            orderLines.remove(adapterPosition);
        }
        transactionOrderLines.setValue(orderLines);
    }
}
