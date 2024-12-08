package com.example.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

public class ExternalAuthentication implements Authentication {

    @Serial
    private static final long serialVersionUID = 928236366786L;

    private final boolean authenticated;
    private final String key;

    public ExternalAuthentication(boolean authenticated, String key) {
        this.authenticated = authenticated;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }
}
