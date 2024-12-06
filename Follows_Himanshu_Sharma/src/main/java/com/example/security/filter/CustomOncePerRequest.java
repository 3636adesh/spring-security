package com.example.security.filter;

import com.example.security.authentication.CustomAuthentication;
import com.example.security.authentication.ExternalAuthentication;
import com.example.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomOncePerRequest extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    public CustomOncePerRequest(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication;
        String externalKey = request.getHeader("external-key");
        if (externalKey != null) {
            authentication = new ExternalAuthentication(false, externalKey);
        } else {
            String headerKey = request.getHeader("key");
            authentication = new CustomAuthentication(false, headerKey);
        }
        Authentication authObj = customAuthenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authObj);
        filterChain.doFilter(request, response);
    }
}
