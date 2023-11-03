package com.nagarro.customer.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountId;
    private double accountNumber;
    private String accountHolderName;
    private double Amount;
    private String customerId;
}
