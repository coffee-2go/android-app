package com.android.coffee2go.data;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData;
import com.android.coffee2go.helper.ConfigFirebase;
=======

>>>>>>> f681c06 (Google Maps)
import com.android.coffee2go.models.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AccountDAO {

    private static AccountDAO instance;

    private DatabaseReference firebaseRef;

    private MutableLiveData<Account> account;


    private AccountDAO() {
        firebaseRef = ConfigFirebase.getDatabaseReference();
    }

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public MutableLiveData<Account> getDataLoggedAccount() {
        account = new MutableLiveData<>();
        DatabaseReference loggedAccountRef;
        loggedAccountRef = firebaseRef.child("accounts").child(FirebaseAuthDAO.getInstance().getAccountUid());
        loggedAccountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Account dbAccount = snapshot.getValue( Account.class );

                account.setValue(dbAccount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return account;
    }



    public void save(Account account) {
        firebaseRef.child("accounts").child(account.getId()).setValue(account);
    }

    public void update(Account account) {

        DatabaseReference accountRef = firebaseRef
                .child("accounts")
                .child( account.getId() );

        Map<String, Object> accountData = convertToMap(account);
        accountRef.updateChildren( accountData );
    }

    private Map<String, Object> convertToMap(Account account) {
        HashMap<String, Object> accountMap = new HashMap<>();
        accountMap.put("id", account.getId());
        accountMap.put("username", account.getUsername());
        accountMap.put("email", account.getEmail());
        accountMap.put("urlPicture", account.getUrlPicture());

        return accountMap;
    }
}
