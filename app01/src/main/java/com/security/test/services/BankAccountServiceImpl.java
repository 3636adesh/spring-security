package com.security.test.services;

import com.security.model.BankAccount;


public class BankAccountServiceImpl implements BankAccountService {

    @Override
    public BankAccount findById(int id) {
//        BankAccount adesh = new BankAccount(id, "adesh", "123553440", 9090.01);
//        var proxyFactory = AuthorizationAdvisorProxyFactory.withDefaults();
//        return (BankAccount) proxyFactory.proxy(adesh);
        return new BankAccount(id, "adesh", "123553440", 9090.01);
    }

    @Override
    public BankAccount getById(int id) {
        return findById(id);
    }
}
