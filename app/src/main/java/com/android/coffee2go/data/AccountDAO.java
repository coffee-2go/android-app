package com.android.coffee2go.data;

import com.android.coffee2go.models.Account;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Michal Pupák
 * **/
public class AccountDAO {
    private DatabaseReference reference;
    private static AccountDAO instance;

    private AccountDAO() {
        FirebaseDatabase database = FirebaseDatabase
                .getInstance("https://coffee2go-baf44-default-rtdb.europe-west1.firebasedatabase.app");
        reference = database.getReference(Account.class.getSimpleName());
    }

    public static synchronized AccountDAO getInstance() {
        if (instance == null){
            instance = new AccountDAO();
        }
        return instance;
    }

    public Task<Void> addAccount(Account account){
        return reference.push().setValue(account);
    }
}
