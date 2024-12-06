package com.example.security.manager;

import com.example.security.provider.CustomAuthenticationProvider;
import com.example.security.provider.ExternalAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final ExternalAuthenticationProvider externalAuthenticationProvider;

    public CustomAuthenticationManager(CustomAuthenticationProvider customAuthenticationProvider, ExternalAuthenticationProvider externalAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.externalAuthenticationProvider = externalAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (customAuthenticationProvider.supports(authentication.getClass())) {
            return customAuthenticationProvider.authenticate(authentication);
        } else {
            return externalAuthenticationProvider.authenticate(authentication);
        }
    }
}
