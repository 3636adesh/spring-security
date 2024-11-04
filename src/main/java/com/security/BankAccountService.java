package com.security;

import com.security.model.BankAccount;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

public class BankAccountService {

        public BankAccount findById(int id) {
           var principal = SecurityContextHolder.getContext().getAuthentication();
            BankAccount adesh = new BankAccount(id, "adesh", "123553440", 9090.01);
            if(!adesh.getOwner().equals(principal.getName())){
                throw new AuthorizationDeniedException("Denied",new AuthorizationDecision(false));
            }
            return adesh;
        }
}
