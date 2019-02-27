package com.victorbarca.rbtapp.controllers;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.TransferData;
import com.victorbarca.rbtapp.services.AccountService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class AccountController {

    private AccountService accountService = new AccountService();
    private UserController userController;

    public AccountController(UserController userController) {
        this.userController = userController;
    }

    public Account getAccount(Integer accountId) throws Exception {
        if (accountService.accountExists(accountId))
            return accountService.getAccount(accountId);
        else throw new Exception("Account not found");
    }

    /*
    To create an account an existing user must be provided
    and by default the account has no balance
     */
    public void createAccount(Account account) throws Exception {
        if(!accountService.accountExists(account.getAccountId())) {
            if (userController.userExists(account.getUserId())) {
                account.setBalance(BigDecimal.ZERO);
                this.accountService.createAccount(account);
            }else throw new Exception("User ID not exists");
        }else throw new Exception("Account ID already exists");
    }

    public void deleteAccount(Integer accountId) throws Exception {
        if (accountService.accountExists(accountId))
            this.accountService.deleteAccount(accountId);
        else throw new Exception("Account not found");
    }

    public void updateAccount(Account account) throws Exception {
        if (accountService.accountExists(account.getAccountId()))
            this.accountService.updateAccount(account);
        else throw new Exception("Account not found");
    }

    public BigDecimal getBalance(Integer accountId) {
        if (accountService.accountExists(accountId))
            return accountService.getBalance(accountId);
        else return null;
    }

    public void withdraw(Integer accountIdFrom, BigDecimal amount) throws Exception {
        if (accountService.getBalance(accountIdFrom).subtract(amount).compareTo(BigDecimal.ZERO) >=0){
            accountService.withdraw(accountIdFrom, amount);
        }else throw new Exception("Account not found or not enough funds");
    }

    public void deposit(Integer accountIdTo, BigDecimal amount)  {
            accountService.deposit(accountIdTo, amount);
    }

    // In order to block the access to any other thread while one is making a transfer I'm asking to lock the accounts involved at Object level.
    // To avoid deadlocks (one thread waiting for the lock that other thread has and this last one waiting as well for the lock of the first one.)
    // I'm locking the accounts always in the same order (min accountId first)
    public void transfer(TransferData transferData) throws Exception {
        if (!Objects.equals(transferData.accountIdFrom, transferData.accountIdTo)) {
            if (accountService.accountExists(transferData.accountIdFrom) && accountService.accountExists(transferData.accountIdTo)) {
                try {
                    Account account1 = transferData.accountIdFrom < transferData.accountIdTo ? accountService.getAccount(transferData.accountIdFrom) : accountService.getAccount(transferData.accountIdTo);
                    Account account2 = transferData.accountIdFrom < transferData.accountIdTo ? accountService.getAccount(transferData.accountIdTo) : accountService.getAccount(transferData.accountIdFrom);
                    System.out.println("[Thread "+Thread.currentThread().getName() + "] Trying to lock account "+account1.getAccountId() +"....");
                    synchronized (account1) {
                        System.out.println("[Thread "+Thread.currentThread().getName() + "] Account "+account1.getAccountId() +" LOCKED");
                        System.out.println("[Thread "+Thread.currentThread().getName() + "] Trying to lock account "+account2.getAccountId() +"....");
                        synchronized (account2) {
                            System.out.println("[Thread "+Thread.currentThread().getName() + "] Account "+account2.getAccountId() +" LOCKED");
                            this.withdraw(transferData.accountIdFrom, transferData.amount);
                            System.out.println("[Thread "+Thread.currentThread().getName() + "] Sending money...");
                            Thread.sleep(5000); // To simulate sending money
                            this.deposit(transferData.accountIdTo, transferData.amount);
                            System.out.println("[Thread "+Thread.currentThread().getName() + "] Transfer successful. " +
                                    "Balance account " + transferData.accountIdFrom + ": " + getBalance(transferData.accountIdFrom) +
                                    " Balance account " + transferData.accountIdTo + ": " + getBalance(transferData.accountIdTo)
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw e;
                }
            }
        }
    }

    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }
}
