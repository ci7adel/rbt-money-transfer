package com.victorbarca.rbtapp.data;

import java.math.BigDecimal;

public class Account {
    private Integer accountId;
    private Integer userId;
    private BigDecimal balance;

    //{"accountId" : 4, "userId" : 1, "balance" : 0}

    public Account(Integer accountId,Integer userId, BigDecimal balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public synchronized BigDecimal getBalance() {
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {}
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addAmount(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
