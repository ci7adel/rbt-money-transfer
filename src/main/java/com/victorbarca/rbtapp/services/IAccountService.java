package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;

public interface IAccountService {

    public Account getAccount(String accountId);

    public void createAccount(Account account);

    public void deleteAccount(String accountId);

    public void updateAccount(Account account);
}
