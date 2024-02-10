package com.example.demo.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;

@Service
public class SchedulerService {
    
    List<Customer> customerList;

    public SchedulerService() {
        this.customerList = new ArrayList<>();
    }

    public int addCustomer(Customer customer) {
        this.customerList.add(customer);
        return this.customerList.size();
    }
}
