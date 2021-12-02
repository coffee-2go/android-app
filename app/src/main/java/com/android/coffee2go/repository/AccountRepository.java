package com.android.coffee2go.repository;

//import com.google.firebase.auth.FirebaseUser;

import com.android.coffee2go.data.AccountDAO;
import com.android.coffee2go.models.Account;

public class AccountRepository {

    private static AccountRepository instance;
    private final AccountDAO accountDAO;

    private AccountRepository() {
        accountDAO = AccountDAO.getInstance();
    }

    public static synchronized AccountRepository getInstance() {
        if(instance == null)
            instance = new AccountRepository();
        return instance;
    }

    public void AddAccount(Account account) {
        accountDAO.addAccount(account);
    }
}
