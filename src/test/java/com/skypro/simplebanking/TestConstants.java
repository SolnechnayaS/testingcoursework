package com.skypro.simplebanking;

import com.skypro.simplebanking.dto.*;
import com.skypro.simplebanking.entity.AccountCurrency;

import java.util.List;

public class TestConstants {

    //AccountDTO
    public static final long ID_ACCOUNT_DTO = 1;
    public static final long AMOUNT_ACCOUNT_DTO = 10000;
    public static final AccountCurrency CURRENCY_ACCOUNT_DTO = AccountCurrency.RUB;
    public static final AccountDTO ACCOUNT_DTO = new AccountDTO(ID_ACCOUNT_DTO, AMOUNT_ACCOUNT_DTO, CURRENCY_ACCOUNT_DTO);

    //BalanceChangeRequest
    public static long AMOUNT_BALANCE_CHANGE_REQUEST = 5000;


    //BankingUserDetails
    public static final long ID_BANKING_USER_DETAILS = 1;
    public static final String USERNAME_BANKING_USER_DETAILS = "user1";
    public static final String PASSWORD_BANKING_USER_DETAILS = "user1234";
    public static final boolean IS_ADMIN = false;
    public static final BankingUserDetails BANKING_USER_DETAILS = new BankingUserDetails(ID_BANKING_USER_DETAILS, USERNAME_BANKING_USER_DETAILS, PASSWORD_BANKING_USER_DETAILS, IS_ADMIN);

    //CreateUserRequest
    public static String USERNAME_CREATE_USER_REQUEST = "user2";
    public static String PASSWORD_CREATE_USER_REQUEST = "user1234";

    //ListAccountDTO
    public static final Long ACCOUNT_ID_LIST_ACCOUNT_DTO = 1L;
    public static final AccountCurrency CURRENCY_LIST_ACCOUNT_DTO = AccountCurrency.RUB;
    public static final ListAccountDTO LIST_ACCOUNT_DTO = new ListAccountDTO(ACCOUNT_ID_LIST_ACCOUNT_DTO, CURRENCY_LIST_ACCOUNT_DTO);

    //ListUserDTO
    public static final long ID_LIST_USER_DTO = 1;
    public static final String USERNAME_LIST_USER_DTO = "user1";
    private static final List<ListAccountDTO> ACCOUNTS = List.of(LIST_ACCOUNT_DTO);
    public static final ListUserDTO LIST_USER_DTO = new ListUserDTO(ID_LIST_USER_DTO, USERNAME_LIST_USER_DTO, ACCOUNTS);

    // TransferRequest
    public static final long FROM_ACCOUNT_ID_TRANSFER_REQUEST = 1;
    public static final long TO_USER_ID_TRANSFER_REQUEST = 2;
    public static final long TO_ACCOUNT_ID_TRANSFER_REQUEST = 4;
    public static final long AMOUNT_TRANSFER_REQUEST = 5000;

    //  UserDTO
    public static final long ID_USER_DTO = 3;
    public static final String USERNAME_USER_DTO = "user";
    public static final List<AccountDTO> ACCOUNTS_USER_DTO = List.of(ACCOUNT_DTO);
    public static final UserDTO USER_DTO = new UserDTO(ID_USER_DTO, USERNAME_USER_DTO, ACCOUNTS_USER_DTO);

}
