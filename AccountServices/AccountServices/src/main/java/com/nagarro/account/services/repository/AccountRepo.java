package com.nagarro.account.services.repository;

import com.nagarro.account.services.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepo extends JpaRepository<Account,String> {
    List<Account> findByCustomerId(String customerId);
    @Transactional
    public void deleteByCustomerId(String customerId);
}
