package com.victorbarca.rbtapp.services;

import com.victorbarca.rbtapp.data.Account;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

public class AccountService implements IAccountService {

    private HashMap<Integer, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
        accounts.put(1, new Account(1,1, BigDecimal.valueOf(1000)));
        accounts.put(2, new Account(2,1, BigDecimal.valueOf(10)));
        accounts.put(3, new Account(3,1, BigDecimal.valueOf(1000)));
        accounts.put(4, new Account(4,1, BigDecimal.valueOf(10)));
    }

    @Override
    public Account getAccount(Integer accountId){
        return accounts.get(accountId);
    }

    @Override
    public void createAccount(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        this.accounts.remove(accountId);
    }

    @Override
    public void updateAccount(Account account) {}

    @Override
    public BigDecimal getBalance(Integer accountId) {
        return accounts.get(accountId).getBalance();
    }

    @Override
    public void withdraw(Integer accountId, BigDecimal amount) {
        Account account = this.accounts.get(accountId);
        account.setBalance(account.getBalance().subtract(amount));
        this.accounts.put(accountId,account);
    }

    @Override
    public void deposit(Integer accountId, BigDecimal amount) {
        Account account = this.accounts.get(accountId);
        account.setBalance(account.getBalance().add(amount));
        this.accounts.put(accountId,account);

    }

//    @Override
//    public void transfer(TransferData transferData) throws Exception {
//        if (!Objects.equals(transferData.accountIdFrom, transferData.accountIdTo)) {
//            try {
//                // In order to block the access to any other thread while one is making a transfer I'm asking to lock the accounts involved at Object level.
//                // To avoid deadlocks (one thread waiting for the lock that other thread has and this last one waiting as well for the lock of the first one.)
//                // I'm locking the accounts always in the same order (min accountId first)
//                Account account1 = transferData.accountIdFrom < transferData.accountIdTo ? getAccount(transferData.accountIdFrom) : getAccount(transferData.accountIdTo);
//                Account account2 = transferData.accountIdFrom < transferData.accountIdTo ? getAccount(transferData.accountIdTo) : getAccount(transferData.accountIdFrom);
//                System.out.println(Thread.currentThread().getName() + " Locking account 1....");
//                synchronized (account1){
//                    System.out.println(Thread.currentThread().getName() + " account 1 LOCKED");
//                    System.out.println(Thread.currentThread().getName() + " Locking account 2....");
//                    synchronized (account2){
//                        System.out.println(Thread.currentThread().getName() + " account 2 LOCKED");
//                        this.withdraw(transferData.accountIdFrom, transferData.amount);
//                        this.deposit(transferData.accountIdTo, transferData.amount);
//                        System.out.println(Thread.currentThread().getName() + " Transfer successful. " +
//                                "Balance account "+transferData.accountIdFrom+": "+ getBalance(transferData.accountIdFrom) +
//                                " Balance account "+transferData.accountIdTo+": "+ getBalance(transferData.accountIdTo)
//                        );
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                throw e;
//            }
//        }
//    }

    @Override
    public Collection<Account> getAccounts() {
        return this.accounts.values();
    }

    @Override
    public boolean accountExists(Integer accountId) {
        return accounts.containsKey(accountId);
    }
}
