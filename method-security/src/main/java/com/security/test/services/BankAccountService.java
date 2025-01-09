package com.security.test.services;

import com.security.PostReadAccount;
import com.security.model.BankAccount;

public interface BankAccountService {

    @PostReadAccount
    BankAccount findById(int id);

    @PostReadAccount
    BankAccount getById(int id);
}
