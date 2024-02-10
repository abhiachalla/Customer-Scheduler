package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.models.Customer;



@Service
public class CustomerService {
    public String addCustomer(Customer customer, SchedulerService schedulerService) {
        // store in DB
        // send to scheduler
        
        return schedulerService.addCustomer(customer) + " customers added!";
    }
}
