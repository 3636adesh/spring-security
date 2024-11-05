package com.security;

import com.security.test.services.BankAccountService;
import com.security.test.services.BankAccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class AppSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSecurityApplication.class, args);
    }

    @Bean
    BankAccountService bankAccountService() {
        return new BankAccountServiceImpl();
    }
}

