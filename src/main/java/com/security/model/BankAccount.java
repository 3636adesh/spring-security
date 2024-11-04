package com.security.model;


import lombok.Getter;

@Getter
public class BankAccount {

    final int id;
    final String owner;
    final String accountNumber;
    final double balance;

    public BankAccount(int id, String owner, String accountNumber, double balance) {
        this.id = id;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

}
