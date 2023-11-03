package com.nagarro.customer.services.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_customer")
public class Customer {
    @Id
    @Column(name="CustomerID")
    private String customerId;
    @Column(name="NAME")
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="Contact")
    private String contact;

    @Transient
    private List<Account> account =new ArrayList<>();

}
