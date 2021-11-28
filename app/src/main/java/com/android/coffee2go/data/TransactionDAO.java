package com.android.coffee2go.data;

import com.android.coffee2go.models.Transaction;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TransactionDAO {
    private DatabaseReference reference;
    private static TransactionDAO instance;

    private TransactionDAO(){
        FirebaseDatabase database = FirebaseDatabase
                .getInstance("https://coffee2go-baf44-default-rtdb.europe-west1.firebasedatabase.app");
        reference = database.getReference(Transaction.class.getSimpleName());
    }

    public static TransactionDAO getInstance(){
        if (instance == null){
            instance = new TransactionDAO();
        }
        return instance;
    }

    public Task<Void> addTransaction(Transaction transaction){
        return reference.push().setValue(transaction);
    }
}
