package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class Config {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

//    @Bean
//    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults("APPLICATION_ROLE");  instead of ROLE_
//    }

    /**
     * Define role hierarchy:
     * ADMIN ⇒ STAFF ⇒ USER ⇒ GUEST
     */
    @Bean
    static RoleHierarchy roleHierarchy() {
//        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > permission:read");
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("STAFF")
                .role("STAFF").implies("USER")
                .role("USER").implies("GUEST")
                .role("ROLE_ADMIN").implies("permission:read")
                .build();
    }

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        final String encodedPassword = passwordEncoder.encode("pw");

        var admin = User.builder()
                .username("admin")
                .password(encodedPassword)
                .roles("ADMIN")
                .build();

        var staff = User.builder()
                .username("staff")
                .password(encodedPassword)
                .roles("STAFF")
                .build();

        var user = User.builder()
                .username("user")
                .password(encodedPassword)
                .roles("USER")
                .build();

        var guest = User.builder()
                .username("guest")
                .password(encodedPassword)
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(admin, staff, user, guest);
    }
}
