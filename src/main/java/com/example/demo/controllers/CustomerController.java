package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.domain.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/checkIn")
    public String checkIn(@RequestBody Customer customer) {

        return customerService.addCustomer(customer);
    }

    @GetMapping("/sequenceNumber")
    public String getMethodName(@RequestBody Customer customer) {

        return customerService.getSequenceNumber(customer);
    }
}
