package com.nagarro.account.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts_table")
public class Account {
    @Id
    @Column(name="AccountID")
    private String accountId;
    @Column(name="accountNumber")
    private double accountNumber;
    @Column(name="accountHolderName")
    private String accountHolderName;
    @Column(name="Amount")
    private double Amount;
    @Column(name="customerId")
    private String customerId;

}
