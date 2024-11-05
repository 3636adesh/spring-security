package com.security;

import com.security.test.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
class AppSecurityApplicationTests {



 //   BankAccountService bankAccountService  =(BankAccountService) AuthorizationAdvisorProxyFactory.withDefaults().proxy(new BankAccountServiceImpl());

    @Autowired
    BankAccountService bankAccountService;


    @WithMockUser("adesh")
    @Test
     void findById() {
   //     SecurityContextHolder.getContext().setAuthentication( new TestingAuthenticationToken("adesh","password","ROLE_USER"));
        bankAccountService.findById(1);

    }

    @WithMockUser("yash")
    @Test
    void findByIdWhenDenied(){
        assertThatExceptionOfType(AuthorizationDeniedException.class)
        .isThrownBy(() -> bankAccountService.findById(1));
    }

    @WithMockUser("adesh")
    @Test
    void getById() {
        bankAccountService.getById(1);

    }

    @WithMockUser("yash")
    @Test
    void getByIdWhenDenied(){
        assertThatExceptionOfType(AuthorizationDeniedException.class)
                .isThrownBy(this::getById);
    }

    @WithMockUser(username = "account",roles = "ACCOUNTANT")
    @Test
    void getByIdWhenAccountant() {
        bankAccountService.getById(1);

    }

    @WithMockUser(username = "account",roles = "ACCOUNTANT")
    @Test
    void findByIdWhenAccountant() {
        bankAccountService.findById(1);

    }

    @WithMockUser(username = "accountant", roles = "ACCOUNTANT")
    @Test
    void findByIdWhenAccountantNumber() {
        assertThat(bankAccountService.findById(1).getAccountNumber()).isEqualTo("****");
    }

}
