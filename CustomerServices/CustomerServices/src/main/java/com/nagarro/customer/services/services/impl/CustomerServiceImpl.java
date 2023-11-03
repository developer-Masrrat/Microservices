package com.nagarro.customer.services.services.impl;

import com.nagarro.customer.services.exception.ResourceNotFoundException;
import com.nagarro.customer.services.models.Account;
import com.nagarro.customer.services.models.Customer;
import com.nagarro.customer.services.repositories.CustomerRepo;
import com.nagarro.customer.services.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Customer addCustomer(Customer customer) {
        //generate Unique Customerid
        String randomCustomerId = UUID.randomUUID().toString();
        customer.setCustomerId(randomCustomerId);
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> allCustomer = customerRepo.findAll();
        return allCustomer;
    }

    @Override
    public Customer getCustomer(String customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with given ID not found ! :" + customerId));
        ArrayList<Account> customerAccount = restTemplate.getForObject("http://ACCOUNT-SERVICE/accounts/customers/"+customer.getCustomerId(), ArrayList.class);
        logger.info("{}",customerAccount);
        customer.setAccount(customerAccount);
        return  customer;

    }

    @Override
    public Customer updateCustomer(Customer customer, String customerId) {
        Customer updateCustomer = customerRepo.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer with the given id not found!!:" + customerId));

        updateCustomer.setName(customer.getName());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setContact(customer.getContact());

        return  customerRepo.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<Customer> isCustomerExist = customerRepo.findById(customerId);
        if (isCustomerExist.isPresent()) {
            customerRepo.deleteById(customerId);
        } else {
            throw new NoSuchElementException("Customer is not exist with given customerId: " + customerId);

        }
    }
}
