package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableMethodSecurity
public class AppSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSecurityApplication.class, args);
    }
}

enum Role {
    ADMIN, USER
}

@Configuration
class SecurityConfig {

    @Bean
    UserDetailsService inMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
        var admin = User.builder()
                .username("admin")
                .password("pw")
                .passwordEncoder(passwordEncoder::encode)
                .authorities(Role.ADMIN.name()).build();

        var user = User.builder()
                .username("user")
                .password("pw")
                .passwordEncoder(passwordEncoder::encode)
                .authorities(Role.USER.name()).build();
        
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

