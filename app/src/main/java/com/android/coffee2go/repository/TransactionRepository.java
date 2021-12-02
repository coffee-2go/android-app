package com.android.coffee2go.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.models.Transaction;

import java.util.List;

public class TransactionRepository {
    private static TransactionRepository instance;
    private Transaction currentTransaction;
    private MutableLiveData<Double> transactionTotal;
    private MutableLiveData<List<OrderLine>> transactionOrderLines;

    private TransactionRepository(){
        currentTransaction = new Transaction();
        Log.i("TRANSACTION REPO","NEW TRANSACTION IS CREATED");
        transactionOrderLines = new MutableLiveData<>();
        transactionOrderLines.setValue(currentTransaction.getOrderLines());

        transactionTotal = new MutableLiveData<>();
        transactionTotal.setValue(currentTransaction.getTransactionTotal());
    }

    public void addOrderLine(OrderLine orderLine) {
        currentTransaction.addOrderLine(orderLine);
        updateLiveData();
        System.out.println("TRANSACTION REPOSITORY ==> ORDER LINE ADDED, NUMBER OF ORDERS: "
                +currentTransaction.getOrderLines().size());
    }

    public static synchronized TransactionRepository getInstance(){
        if (instance == null){
            instance = new TransactionRepository();
        }
        return instance;
    }

    public void removeOrderLine(int adapterPosition) {
        currentTransaction.getOrderLines().remove(adapterPosition);
        updateLiveData();
        System.out.println("TRANSACTION REPOSITORY ==> ORDER LINE REMOVED, NUMBER OF ORDERS: "
                +currentTransaction.getOrderLines().size());
    }

    public void changeQuantity(int position, int newQuantity){
        currentTransaction.getOrderLines().get(position).setQuantity(newQuantity);
        updateLiveData();
        System.out.println("TRANSACTION REPOSITORY ==> ORDER LINE QUANTITY CHANGE, ORDER LINE QUANTITY: "
                +currentTransaction.getOrderLines().get(position).getQuantity());

    }

    private void updateLiveData() {
        transactionOrderLines.setValue(currentTransaction.getOrderLines());
        transactionTotal.setValue(currentTransaction.getTransactionTotal());
    }

    public LiveData<List<OrderLine>> getTransactionOrderLines() {
        return transactionOrderLines;
    }

    public LiveData<Double> getTransactionTotal() {
        return transactionTotal;
    }
}
