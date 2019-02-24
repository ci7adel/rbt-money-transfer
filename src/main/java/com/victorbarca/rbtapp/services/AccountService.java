package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;

public class AccountService implements IAccountService {

    private HashMap<String, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
        accounts.put("A00001", new Account("A00001", "A00001", BigDecimal.valueOf(10000)));
        accounts.put("A00002", new Account("A00001", "A00002", BigDecimal.valueOf(20000)));
        accounts.put("A00003", new Account("A00001", "A00001", BigDecimal.valueOf(30000)));
    }

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public void createAccount(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }

    @Override
    public void deleteAccount(String accountId) {}

    @Override
    public void updateAccount(Account account) {}

    @Override
    public BigDecimal checkBalance(String accountId) {
        return accounts.containsKey(accountId) ? accounts.get(accountId).getBalance() : null ;
    }

    @Override
    public synchronized void withdraw(String accountIdFrom, BigDecimal amount) throws Exception {
        if (accounts.containsKey(accountIdFrom) && accounts.get(accountIdFrom).getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0){
            Account account = this.getAccount(accountIdFrom);
            account.substractAmount(amount);
            this.accounts.put(accountIdFrom,account);
        }else throw new Exception("Account not found or not enough funds");
    }

    @Override
    public void deposit(String accountIdTo, BigDecimal amount) {

    }

    @Override
    public synchronized void transfer(Transaction transaction) throws Exception {
        // ToDo: block amounts
        // ToDo: check balances
//        try {
//            wait(5000);
//        } catch (Exception e) {}

        try {
            this.withdraw(transaction.accountIdFrom, transaction.amount);
            this.deposit(transaction.accountIdTo, transaction.amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }
}
