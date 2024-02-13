package com.example.demo.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Employee")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eID;

    @Column(name = "name")
    private String name;
    
    @Column(name = "sequentialServiceNumberServing")
    private int sequentialServiceNumberServing;

    private boolean isAvailable;

    public Employee(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public Employee() {
    }

    public String serveCustomer(Customer customer) {

        if(customer.getCustomerType() == CustomerType.EMPTY) {
            return "No customers to serve";
        }

        if(customer.getCustomerType() == CustomerType.WAITING) {
            return "No available employees to serve the customer, please wait!!!";
        }
        
        // this.currentServingCustomer = customer;
        this.isAvailable = false;
        
        String customerRequests = customer.getRequests();
        StringBuilder employeeResponses = new StringBuilder();

        for (String request : customerRequests.split(",")) {
            employeeResponses.append("Request ******" + request + "****** completed, ");
        }
        
        return employeeResponses.toString();
    }
    
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public String getName() {
        return this.name;
    }

    public int getSequentialServiceNumberServing() {
        return this.sequentialServiceNumberServing;
    }   

    public void setSequentialServiceNumberServing(int sequentialServiceNumberServing) {
        this.sequentialServiceNumberServing = sequentialServiceNumberServing;
    }


}
