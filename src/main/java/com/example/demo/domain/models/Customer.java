package com.example.demo.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "Customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cID;
    
    @Column(name = "cname")
    private String name;
    
    @Column(name = "cnumber")
    private String number;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "customerType")
    private CustomerType customerType;

    @Column(name = "requests")
    private String requests;

    @Column(name = "sequentialServiceNumber")
    private int sequentialServiceNumber;

    @Column(name = "ticketNumber")
    private int ticketNumber;

    public Customer(String name, String number, CustomerType customerType, String requests) {
        this.name = name;
        this.number = number;
        this.customerType = customerType;
        this.requests = requests;
    }

    public Customer() {
    }

    public int getcID() {
        return this.cID;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public CustomerType getCustomerType() {
        return this.customerType == null ? CustomerType.EMPTY : this.customerType;
    }

    public String getRequests() {
        return this.requests;
    }

    public int getSequentialServiceNumber() {
        return this.sequentialServiceNumber;
    }

    public void setSequentialServiceNumber(int sequenceNumber) {
        this.sequentialServiceNumber = sequenceNumber;
    }

    public int getTicketNumber() {
        return this.ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
