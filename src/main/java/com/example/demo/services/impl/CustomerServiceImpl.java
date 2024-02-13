package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.springframework.stereotype.Service;
import com.example.demo.services.SchedulerService;

@Service
public class CustomerServiceImpl implements CustomerService{
        
    private SchedulerService schedulerService;
    
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(SchedulerService schedulerService, CustomerRepository customerRepository) {
        this.schedulerService = schedulerService;
        this.customerRepository = customerRepository;
    }

    public String addCustomer(Customer customer) {
        // store in DB
        // send to scheduler
        
        // if(customer exists, return "customer already exists")

        // Optional<Customer> customerExists = customerRepository.findByName(customer.getName());
        

        // if(customerExists.isPresent()) {
        //     return "Customer already exists!";
        // } 

        customerRepository.save(customer);
        
        String response = schedulerService.checkIn(customer);
        return response;
        
    }

    public String getSequenceNumber(Customer customer) {
        // if the customer exists in db, return the ticket number
        // else return "customer not found"
        return "";
    }   
}
