package com.nagarro.account.services.services.impl;


import com.nagarro.account.services.exception.ResourceNotFoundException;
import com.nagarro.account.services.models.Account;
import com.nagarro.account.services.repository.AccountRepo;
import com.nagarro.account.services.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;


    @Override
    public Account addAccount(Account account) {
        String randomUserId= UUID.randomUUID().toString();
        account.setAccountId(randomUserId);
        return accountRepo.save(account);
    }

    @Override
    public Account getAccount(String accountId) {
        return accountRepo.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("account with given id not found"));

    }

    @Override
    public List<Account> getAccountByCustomerId(String customerId) {
        return accountRepo.findByCustomerId(customerId);
    }

    @Override
    public void deleteAccount(String accountId) {
        Optional<Account> isAccountExist = accountRepo.findById(accountId);
        if (isAccountExist.isPresent()) {
            accountRepo.deleteById(accountId);
        } else {
            throw new NoSuchElementException("Account doesn't exist with given accountId: " + accountId);

        }
    }

    @Override
    public void deleteAccountByCustomerId(String customerId) {
        System.out.println(accountRepo.findByCustomerId(customerId));
        Optional<List<Account>> isAccountExist = Optional.ofNullable(accountRepo.findByCustomerId(customerId));

        if (isAccountExist.isPresent()) {
            accountRepo.deleteByCustomerId(customerId);
        } else {
            throw new NoSuchElementException("Account doesn't exist with given customerId: " + customerId);

        }
    }
}
