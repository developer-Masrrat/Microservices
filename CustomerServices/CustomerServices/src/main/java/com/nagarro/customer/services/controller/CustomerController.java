package com.nagarro.customer.services.controller;

import com.nagarro.customer.services.models.Customer;
import com.nagarro.customer.services.payload.ApiResponse;
import com.nagarro.customer.services.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create Customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer customer1 = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
    }
    //Get Single Customer
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getSingleCustomer(@PathVariable String customerId){
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer);
    }

    //Get All Customer
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> allCustomer = customerService.getAllCustomer();
        return ResponseEntity.ok(allCustomer);
    }

    //Update Customer
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer , @PathVariable String customerId){
        Customer updatecustomer = customerService.updateCustomer(customer , customerId);
        return  ResponseEntity.status(HttpStatus.OK).body(updatecustomer);
    }

    // Delete Customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String customerId) {
        this.customerService.deleteCustomer(customerId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deletd successfully", true, HttpStatus.OK),
                HttpStatus.OK);
    }
}
