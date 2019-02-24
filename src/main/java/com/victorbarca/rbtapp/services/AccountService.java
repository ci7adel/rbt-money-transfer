package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;

import java.math.BigDecimal;

public class AccountService implements IAccountService {
    @Override
    public Account getAccount(String accountId) {
        return new Account(accountId, "A00001", BigDecimal.valueOf(100));
    }

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public void deleteAccount(String accountId) {

    }

    @Override
    public void updateAccount(Account account) {

    }
}
