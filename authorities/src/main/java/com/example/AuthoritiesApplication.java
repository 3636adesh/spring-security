package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@SpringBootApplication
public class AuthoritiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthoritiesApplication.class, args);
    }

}


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
@interface IsAdmin {
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('STAFF')")
@interface IsStaff {
}


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('USER')")
@interface IsUser {
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('GUEST')")
@interface IsGuest {
}
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('permission:read')")
@interface IsPermission {}

@RestController
class AuthoritiesController {

    @IsGuest
    @GetMapping
    public String admin(@AuthenticationPrincipal User user) {
        return "Hello @" + user.getUsername() + ", with roles " + user.getAuthorities();
    }

    @IsPermission
    @GetMapping("/permissions")
    public List<String>  permissions() {
        return List.of("ADMIN", "GUEST" , "USER" , "STAFF" , "GUEST");
    }
}

