package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.TransferData;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class AccountService implements IAccountService {

    private HashMap<Integer, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
        accounts.put(1, new Account(1,1, BigDecimal.valueOf(10000)));
        accounts.put(2, new Account(2,1, BigDecimal.valueOf(20000)));
        accounts.put(3, new Account(3,1, BigDecimal.valueOf(30000)));
    }

    @Override
    public Account getAccount(Integer accountId) throws Exception {
        if (accounts.containsKey(accountId))
            return accounts.get(accountId);
        else throw new Exception("Account not found");
    }

    @Override
    public void createAccount(Account account) {
        account.setBalance(BigDecimal.ZERO); // Account balance just created must be 0
        this.accounts.put(account.getAccountId(), account);
    }

    @Override
    public void deleteAccount(Integer accountId) throws Exception {
        if (accounts.containsKey(accountId))
            this.accounts.remove(accountId);
        else throw new Exception("Account not found");
    }

    @Override
    public void updateAccount(Account account) {}

    @Override
    public BigDecimal getBalance(Integer accountId) {
        return accounts.containsKey(accountId) ? accounts.get(accountId).getBalance() : null ;
    }

    @Override
    public void withdraw(Integer accountIdFrom, BigDecimal amount) throws Exception {
        if (accounts.containsKey(accountIdFrom) && accounts.get(accountIdFrom).getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0){
            Account account = this.getAccount(accountIdFrom);
            account.substractAmount(amount);
            this.accounts.put(accountIdFrom,account);
        }else throw new Exception("Account not found or not enough funds");
    }

    @Override
    public void deposit(Integer accountIdTo, BigDecimal amount) throws Exception {
        if (accounts.containsKey(accountIdTo)){
            Account account = this.getAccount(accountIdTo);
            account.addAmount(amount);
            this.accounts.put(accountIdTo,account);
        }else throw new Exception("Account not found");
    }

    @Override
    public void transfer(TransferData transferData) throws Exception {
        if (!Objects.equals(transferData.accountIdFrom, transferData.accountIdTo)) {
            try {
                // In order to block the access to any other thread while one is making a transfer I'm asking to lock the accounts involved at Object level.
                // To avoid deadlocks (one thread waiting for the lock that other thread has and this last one waiting as well for the lock of the first one.)
                // I'm locking the accounts always in the same order (min accountId first)
                Account account1 = transferData.accountIdFrom < transferData.accountIdTo ? getAccount(transferData.accountIdFrom) : getAccount(transferData.accountIdTo);
                Account account2 = transferData.accountIdFrom < transferData.accountIdTo ? getAccount(transferData.accountIdTo) : getAccount(transferData.accountIdFrom);
                System.out.println(Thread.currentThread().getName() + " Locking account 1....");
                synchronized (account1){
                    System.out.println(Thread.currentThread().getName() + " account 1 LOCKED");
                    System.out.println(Thread.currentThread().getName() + " Locking account 2....");
                    synchronized (account2){
                        System.out.println(Thread.currentThread().getName() + " account 2 LOCKED");
                        this.withdraw(transferData.accountIdFrom, transferData.amount);
                        this.deposit(transferData.accountIdTo, transferData.amount);
                        System.out.println(Thread.currentThread().getName() + " Transfer successful. " +
                                "Balance account "+transferData.accountIdFrom+": "+ getBalance(transferData.accountIdFrom) +
                                " Balance account "+transferData.accountIdTo+": "+ getBalance(transferData.accountIdTo)
                        );
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }

    @Override
    public Collection<Account> getAccounts() {
        return this.accounts.values();
    }
}
