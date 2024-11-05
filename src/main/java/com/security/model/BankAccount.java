package com.security.model;


import com.security.MaskMethodAuthorizationDeniedHandler;
import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;

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

    @PreAuthorize("this.owner == authentication?.name")
    @HandleAuthorizationDenied(handlerClass = MaskMethodAuthorizationDeniedHandler.class)
    public String getAccountNumber() {
        return accountNumber;
    }

}
