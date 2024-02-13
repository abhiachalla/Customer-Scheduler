package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;

@Service
public interface CustomerService {

    public String addCustomer(Customer customer);

    public String getSequenceNumber(Customer customer);

} 