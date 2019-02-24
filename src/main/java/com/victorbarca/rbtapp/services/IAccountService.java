package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.Transaction;

import java.math.BigDecimal;

public interface IAccountService {

    public Account getAccount(String accountId);

    public void createAccount(Account account);

    public void deleteAccount(String accountId);

    public void updateAccount(Account account);

    public BigDecimal checkBalance(String accountId);

    public void withdraw(String accountIdFrom, BigDecimal amount) throws Exception;

    public void deposit(String accountIdTo, BigDecimal amount);

    void transfer(Transaction transaction) throws Exception;
}
