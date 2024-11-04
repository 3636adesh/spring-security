package com.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.nio.file.AccessDeniedException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
class AppSecurityApplicationTests {


    BankAccountService bankAccountService = new BankAccountService();


    @WithMockUser("adesh")
    @Test
     void findById() {
   //     SecurityContextHolder.getContext().setAuthentication( new TestingAuthenticationToken("adesh","password","ROLE_USER"));
        bankAccountService.findById(1);

    }

    @WithMockUser("yash")
    @Test
    void findByIdWhenDenied(){
        assertThatExceptionOfType(AccessDeniedException.class)
        .isThrownBy(() -> bankAccountService.findById(1));
    }

}
