package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.models.Customer;

@Service
public interface CustomerService {

    public String addCustomer(Customer customer);

    public String getSequenceNumber(Customer customer);

} 