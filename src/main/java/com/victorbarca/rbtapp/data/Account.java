package com.victorbarca.rbtapp.data;

import java.math.BigDecimal;

public class Account {
    public String accountId;
    public String accountNumber;
    public BigDecimal balance;
    public BigDecimal blockeddBalance;

    public Account(String accountId, String accountNumber, BigDecimal balance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.blockeddBalance = BigDecimal.ZERO;
    }
}
