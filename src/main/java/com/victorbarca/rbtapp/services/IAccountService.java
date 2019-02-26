package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.TransferData;

import java.math.BigDecimal;
import java.util.Collection;

public interface IAccountService {

    public Account getAccount(Integer accountId) throws Exception;

    public void createAccount(Account account);

    public void deleteAccount(Integer accountId) throws Exception;

    public void updateAccount(Account account);

    public BigDecimal getBalance(Integer accountId);

    public void withdraw(Integer accountIdFrom, BigDecimal amount) throws Exception;

    public void deposit(Integer accountIdTo, BigDecimal amount) throws Exception;

    public void transfer(TransferData transaction) throws Exception;

    public Collection<Account> getAccounts();
}
