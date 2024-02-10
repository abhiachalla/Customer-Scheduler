package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import com.example.demo.services.SchedulerService;




@RestController
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @Autowired
    SchedulerService schedulerService;

    @PostMapping("/checkIn")
    public String checkIn(@RequestBody Customer customer) {
        return customerService.addCustomer(customer, schedulerService);
    }
    
}
