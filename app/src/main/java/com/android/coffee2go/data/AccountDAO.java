package com.android.coffee2go.data;

import android.util.Log;

import androidx.annotation.NonNull;
import com.android.coffee2go.models.Account;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;


public class AccountDAO {
    private DatabaseReference reference;
    private static AccountDAO instance;

    private AccountDAO() {
        FirebaseDatabase database = FirebaseDatabase
                .getInstance("https://coffee2go-baf44-default-rtdb.europe-west1.firebasedatabase.app");
        reference = database.getReference("accounts");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    HashMap<String,String> map = (HashMap<String, String>) child.getValue();
                    Account account = new Account(map.get("username"),map.get("email"),map.get("password"));
                    Log.i("ACCOUNT DAO","ACCOUNT: "+account.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("ACCOUNT DAO", "Failed to read value.", error.toException());
            }
        });
    }

    public static synchronized AccountDAO getInstance() {
        if (instance == null){
            instance = new AccountDAO();
        }
        return instance;
    }

    public Task<Void> addAccount(Account account){
        reference.child(account.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Log.i("ACCOUNT DAO","USERNAME ALREADY EXISTS");
                }
                else {
                    reference.child(account.getUsername()).setValue(account);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return null;
    }

//    public Account getAccount(String id){
//        final Account[] accounts = {new Account()};
//        reference.child(id).get().addOnCompleteListener(task -> {
//            if (!task.isSuccessful()) {
//                Log.e("firebase", "Error getting data", task.getException());
//            }
//            else {
//                HashMap<String, String> value = (HashMap<String, String>) task.getResult().getValue();
//                Log.i("ACCOUNT DAO GET ACCOUNT",value.toString());
//                Account account = new Account();
//                account.setId(value.get("id"));
//                account.setUsername(value.get("username"));
//                account.setUrlPicture(value.get("urlPicture"));
//                account.setEmail(value.get("email"));
//                Log.i("ACCOUNT DAO GET ACCOUNT",account.toString());
//                //account[0] = new Account(map.get("username"), map.get("email"), map.get("password"));
//            }
//        });
//        return accounts[0];
//    }
}
