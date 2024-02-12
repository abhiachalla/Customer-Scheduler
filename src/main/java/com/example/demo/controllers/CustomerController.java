package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
public class CustomerController {
    

    CustomerService customerService;

    public CustomerController() {
    }   

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/checkIn")
    public String checkIn(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/sequenceNumber")
    public String getMethodName(@RequestBody Customer customer) {
        return customerService.getSequenceNumber(customer);
    }
    
    @GetMapping("/checkInTest")
    public String getMethodName() {
        return "check in working";
    }
    
    
}
