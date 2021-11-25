package com.android.coffee2go.persistence;

import com.android.coffee2go.models.Transaction;

/**
 * @author Michal Pupák
 * **/
public class TransactionRepository {
    private static TransactionRepository instance;
    private Transaction currentTransaction;

    private TransactionRepository(){
        currentTransaction = new Transaction();
    }

    public Transaction getTransaction(){
        return currentTransaction;
    }

    public static synchronized TransactionRepository getInstance(){
        if (instance == null){
            instance = new TransactionRepository();
        }
        return instance;
    }
}
