package com.skypro.simplebanking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.simplebanking.entity.Account;
import com.skypro.simplebanking.entity.AccountCurrency;
import com.skypro.simplebanking.entity.User;
import com.skypro.simplebanking.repository.AccountRepository;
import com.skypro.simplebanking.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static com.skypro.simplebanking.TestConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

@Testcontainers
class TransferControllerTest_forAdminRole {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    public void fillRepository() {
        User newUser1 = new User();
        newUser1.setUsername(USERNAME_1);
        newUser1.setPassword(PASSWORD_1BCRYPT);
        userRepository.save(newUser1);

        User newUser2 = new User();
        newUser2.setUsername(USERNAME_2);
        newUser2.setPassword(PASSWORD_2BCRYPT);
        userRepository.save(newUser2);

        Account newAccount1 = new Account();
        newAccount1.setAmount(AMOUNT_ACCOUNT_1);
        newAccount1.setAccountCurrency(AccountCurrency.RUB);
        newAccount1.setUser(userRepository.findByUsername(USERNAME_1).orElseThrow());
        accountRepository.save(newAccount1);

        Account newAccount2 = new Account();
        newAccount2.setAmount(AMOUNT_ACCOUNT_2);
        newAccount2.setAccountCurrency(AccountCurrency.USD);
        newAccount2.setUser(userRepository.findByUsername(USERNAME_1).orElseThrow());
        accountRepository.save(newAccount2);

        Account newAccount3 = new Account();
        newAccount3.setAmount(AMOUNT_ACCOUNT_3);
        newAccount3.setAccountCurrency(AccountCurrency.EUR);
        newAccount3.setUser(userRepository.findByUsername(USERNAME_1).orElseThrow());
        accountRepository.save(newAccount3);

        Account newAccount4 = new Account();
        newAccount4.setAmount(AMOUNT_ACCOUNT_1);
        newAccount4.setAccountCurrency(AccountCurrency.RUB);
        newAccount4.setUser(userRepository.findByUsername(USERNAME_2).orElseThrow());
        accountRepository.save(newAccount4);

        Account newAccount5 = new Account();
        newAccount5.setAmount(AMOUNT_ACCOUNT_2);
        newAccount5.setAccountCurrency(AccountCurrency.USD);
        newAccount5.setUser(userRepository.findByUsername(USERNAME_2).orElseThrow());
        accountRepository.save(newAccount5);

        Account newAccount6 = new Account();
        newAccount6.setAmount(AMOUNT_ACCOUNT_3);
        newAccount6.setAccountCurrency(AccountCurrency.EUR);
        newAccount6.setUser(userRepository.findByUsername(USERNAME_2).orElseThrow());
        accountRepository.save(newAccount6);
    }

    @AfterEach
    public void cleanData() {
        userRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Value("${app.security.admin-token}")
    private String token;

    @Test
    void transfer() throws Exception {

        JSONObject transferRequest1 = new JSONObject();
        transferRequest1.put("fromAccountId", 1);
        transferRequest1.put("toUserId", 2);
        transferRequest1.put("toAccountId", 4);
        transferRequest1.put("amount", 5000);

        mockMvc.perform(post("/transfer")
                        .header(ADMIN_KEY, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transferRequest1.toString()))
                .andExpect(status().isForbidden());

    }
}