package com.example.security.provider;

import com.example.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${app.secret.key}")
    private String authKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var customAuth =(CustomAuthentication) authentication;
        String headerKey = customAuth.getKey();
        if(authKey.equals(headerKey)) {
            return new CustomAuthentication(true,headerKey);
        }
        throw  new BadCredentialsException("Bad security header");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
