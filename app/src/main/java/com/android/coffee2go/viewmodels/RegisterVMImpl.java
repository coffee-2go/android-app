package com.android.coffee2go.viewmodels;

import androidx.lifecycle.ViewModel;

import com.android.coffee2go.models.Account;
import com.android.coffee2go.persistence.AccountRepository;

/**
 * @author Michal Pupák
 * **/
public class RegisterVMImpl extends ViewModel implements RegisterVM {

    private final AccountRepository accountRepository;

    public RegisterVMImpl() {
        accountRepository = AccountRepository.getInstance();
    }

    @Override
    public void AddAccount(Account account) {
        accountRepository.AddAccount(account);
    }
}
