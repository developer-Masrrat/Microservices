package com.nagarro.customer.services.services;

import com.nagarro.customer.services.models.Customer;

import java.util.List;

public interface CustomerService {

    //Customer Operations

    //Add customer
    Customer addCustomer(Customer customer);

    //Get all Customers
    List<Customer> getAllCustomer();

    //Get single Customer Details
    Customer getCustomer(String customerId);

    //Update Customer Details
    Customer updateCustomer(Customer customer ,String customerId);

    //Delete Customer Details
    void deleteCustomer(String customerId);
}
