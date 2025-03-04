package com.security.test.services;

import com.security.model.BankAccount;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

public class BankAccountServiceImplProxy implements BankAccountService {


    final BankAccountServiceImpl delegate;
    public BankAccountServiceImplProxy(BankAccountServiceImpl bankAccountServiceImpl) {
        this.delegate = bankAccountServiceImpl;
    }


    @Override
    public BankAccount getById(int id) {
        return delegate.getById(id);
    }

    @Override
    public BankAccount findById(int id) {

        var principal = SecurityContextHolder.getContext().getAuthentication();
        BankAccount adesh = delegate.findById(id);
        if (!adesh.getOwner().equals(principal.getName())) {
            throw new AuthorizationDeniedException("Denied", new AuthorizationDecision(false));
        }
        return adesh;
    }
}
