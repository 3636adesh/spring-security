package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}

/**
 * Auth server must have info of your user
 */

enum Roles {
    USER, ADMIN
}

@Configuration
class SecurityConfig {

    @Bean
    UserDetailsService inMemoryUserDetails() {
        var one = User.withDefaultPasswordEncoder().username("one").password("pw").roles("ADMIN","USER").build();
        var two = User.withDefaultPasswordEncoder().username("two").password("pw").roles("USER").build();
        return new InMemoryUserDetailsManager(one, two);
    }
}