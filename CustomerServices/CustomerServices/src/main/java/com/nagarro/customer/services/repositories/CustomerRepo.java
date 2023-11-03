package com.nagarro.customer.services.repositories;

import com.nagarro.customer.services.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer , String> {
}
