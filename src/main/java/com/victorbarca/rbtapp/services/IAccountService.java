package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.TransferData;

import java.math.BigDecimal;

public interface IAccountService {

    public Account getAccount(Integer accountId);

    public void createAccount(Account account);

    public void deleteAccount(Integer accountId);

    public void updateAccount(Account account);

    public BigDecimal getBalance(Integer accountId);

    public void withdraw(Integer accountIdFrom, BigDecimal amount) throws Exception;

    public void deposit(Integer accountIdTo, BigDecimal amount) throws Exception;

    void transfer(TransferData transaction) throws Exception;
}
