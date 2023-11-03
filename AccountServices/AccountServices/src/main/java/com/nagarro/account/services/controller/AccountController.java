package com.nagarro.account.services.controller;

import com.nagarro.account.services.models.Account;
import com.nagarro.account.services.repository.AccountRepo;
import com.nagarro.account.services.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepo accountRepo;

    //create Account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(account));

    }

    //Get Account
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account);

    }
//Get Account By customer Id

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable String customerId){
        return ResponseEntity.ok(accountService.getAccountByCustomerId(customerId));
    }
    //Withdraw money from account
    //acNo=customerId
    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<Account> withdrawMoney(@PathVariable String accountId, @RequestBody Account ac) {
        System.out.println("step1");
        System.out.println(accountId);
        System.out.println(ac.toString());
        Account acc = accountRepo.getOne(accountId);
        System.out.println("step2");
        System.out.println(acc.toString());
        if (null != acc) {
            acc.setAmount(acc.getAmount() - ac.getAmount());
            return ResponseEntity.ok(accountRepo.save(acc));
        }
        return null;
    }

    //Add Money to account
    @PutMapping("/deposite/{accountId}")
    public ResponseEntity<Account> depositeMoney(@PathVariable String accountId, @RequestBody Account ac) {
        System.out.println("step1");
        System.out.println(accountId);
        System.out.println(ac.toString());
        Account acc = accountRepo.getOne(accountId);
        System.out.println("step2");
        System.out.println(acc.toString());
        if (null != acc) {
            acc.setAmount(acc.getAmount() + ac.getAmount());
            return ResponseEntity.ok(accountRepo.save(acc));
        }
        return null;
    }

    // Delete Account BY Account Id
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountId) {
        try {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<String>(
                    "Account deleted successfully with given accountId: " + accountId, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // // Delete Account BY Customer Id
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteAccountByCustomerId(@PathVariable("customerId") String customerId) {
        try {
            accountService.deleteAccountByCustomerId(customerId);
            return new ResponseEntity<String>("Account deleted successfully with given customer Id: " + customerId,
                    HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
