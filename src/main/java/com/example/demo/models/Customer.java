package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cID;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "number")
    private String number;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "customerType")
    private CustomerType customerType;

    // how to store customer requests - list of strings in DB??????????????????
    @Column(name = "requests")
    private String requests;

    public Customer(String name, String number, CustomerType customerType, String requests) {
        this.name = name;
        this.number = number;
        this.customerType = customerType;
        this.requests = requests;
    }

    public long getcID() {
        return this.cID;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public CustomerType getCustomerType() {
        return this.customerType;
    }

    // change this to list of strings ???????????????????????????????????????
    public String getRequests() {
        return this.requests;
    }



}
