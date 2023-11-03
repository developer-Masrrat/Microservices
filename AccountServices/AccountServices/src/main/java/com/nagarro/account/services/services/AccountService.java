package com.nagarro.account.services.services;

import com.nagarro.account.services.models.Account;

import java.util.List;

public interface AccountService {
    //create Account
    Account addAccount(Account account);

    //get single Account
    Account getAccount(String accountId);

   List< Account> getAccountByCustomerId(String customerId);

    //delete Account
    //delete Customer
    void deleteAccount(String accountId);
   void deleteAccountByCustomerId(String customerId);
}
