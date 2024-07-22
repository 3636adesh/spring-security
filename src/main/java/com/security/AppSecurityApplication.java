package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AppSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSecurityApplication.class, args);
    }

}

@RestController
class HomeController {

    @GetMapping("/")
    public String home() {
        return "<h1>Security Service</h1>";
    }
}