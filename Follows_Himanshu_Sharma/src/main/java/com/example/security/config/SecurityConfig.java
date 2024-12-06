package com.example.security.config;

import com.example.security.filter.CustomOncePerRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig {

    private final CustomOncePerRequest customOncePerRequest;

    public SecurityConfig(CustomOncePerRequest customOncePerRequest) {
        this.customOncePerRequest = customOncePerRequest;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAfter(customOncePerRequest, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .build();
    }

}
