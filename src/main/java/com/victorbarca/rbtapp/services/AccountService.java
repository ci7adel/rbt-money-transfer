package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.DataBase;

import java.math.BigDecimal;
import java.util.Collection;

public class AccountService implements IAccountService {
    private DataBase dataBase;

    public AccountService() {
        this.dataBase = DataBase.getInstance();

    }

    @Override
    public Account getAccount(Integer accountId){
        return dataBase.accounts.getOrDefault(accountId, null);
    }

    @Override
    public void createAccount(Account account) {
        dataBase.accounts.put(account.getAccountId(), account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        dataBase.accounts.remove(accountId);
    }

    @Override
    public void updateAccount(Account account) {
        dataBase.accounts.put(account.getAccountId(), account);
    }

    @Override
    public BigDecimal getBalance(Integer accountId) {
        return dataBase.accounts.get(accountId).getBalance();
    }

    @Override
    public void withdraw(Integer accountId, BigDecimal amount) {
        Account account = dataBase.accounts.get(accountId);
        account.setBalance(account.getBalance().subtract(amount));
        dataBase.accounts.put(accountId,account);
    }

    @Override
    public void deposit(Integer accountId, BigDecimal amount) {
        Account account = dataBase.accounts.get(accountId);
        account.setBalance(account.getBalance().add(amount));
        dataBase.accounts.put(accountId,account);

    }

    @Override
    public Collection<Account> getAccounts() {
        return dataBase.accounts.values();
    }

    @Override
    public boolean accountExists(Integer accountId) {
        return dataBase.accounts.containsKey(accountId);
    }
}
