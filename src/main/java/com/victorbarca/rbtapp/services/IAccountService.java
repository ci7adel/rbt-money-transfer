package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;

import java.math.BigDecimal;
import java.util.Collection;

public interface IAccountService {

    Account getAccount(Integer accountId) throws Exception;

    void createAccount(Account account);

    void deleteAccount(Integer accountId) throws Exception;

    void updateAccount(Account account);

    BigDecimal getBalance(Integer accountId);

    void withdraw(Integer accountIdFrom, BigDecimal amount) throws Exception;

    void deposit(Integer accountIdTo, BigDecimal amount) throws Exception;

//    void transfer(TransferData transaction) throws Exception;

    Collection<Account> getAccounts();

    boolean accountExists(Integer accountId);

}
