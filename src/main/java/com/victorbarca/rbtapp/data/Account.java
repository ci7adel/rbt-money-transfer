package com.victorbarca.rbtapp.data;

import java.math.BigDecimal;

public class Account {
    private String accountId;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal blockeddBalance;

    public Account(String accountId, String accountNumber, BigDecimal balance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.blockeddBalance = BigDecimal.ZERO;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBlockeddBalance() {
        return blockeddBalance;
    }

    public void setBlockeddBalance(BigDecimal blockeddBalance) {
        this.blockeddBalance = blockeddBalance;
    }

    public void substractAmount(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
