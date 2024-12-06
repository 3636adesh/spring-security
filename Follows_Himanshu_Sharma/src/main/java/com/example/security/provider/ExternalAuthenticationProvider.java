package com.example.security.provider;

import com.example.security.authentication.ExternalAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ExternalAuthenticationProvider implements AuthenticationProvider {

    @Value("${external.secret.key}")
    private String authKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var externalAuth =(ExternalAuthentication) authentication;
        String headerKey = externalAuth.getKey();
        if(authKey.equals(headerKey)) {
            return new ExternalAuthentication(true,headerKey);
        }
        throw  new BadCredentialsException("Bad external security header");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ExternalAuthentication.class.equals(authentication);
    }
}
