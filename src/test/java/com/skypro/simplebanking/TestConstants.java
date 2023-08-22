package com.skypro.simplebanking;

import com.skypro.simplebanking.repository.AccountRepository;
import com.skypro.simplebanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class TestConstants {

    @Autowired
    private static AccountRepository accountRepository;

    @Autowired
    private static UserRepository userRepository;

    public static final String USERNAME_1 = "user1";
    public static final String PASSWORD_1BCRYPT = "$2y$10$09DJUP88eYqJVf4e29c12uAHV4gL41G6knFmchectiGukW1tRb11G";
    public static final String PASSWORD_1DECODE = "user1234";

    public static final String USERNAME_2 = "user2";
    public static final String PASSWORD_2BCRYPT = "$2y$10$09DJUP88eYqJVf4e29c12uAHV4gL41G6knFmchectiGukW1tRb11G";
    public static final String PASSWORD_2DECODE = "user1234";

    public static final Long AMOUNT_ACCOUNT_1 = 10000L;
    public static final Long AMOUNT_ACCOUNT_2 = 5000L;
    public static final Long AMOUNT_ACCOUNT_3 = 1000L;
    public static final String ADMIN_KEY = "X-SECURITY-ADMIN-KEY";

}
