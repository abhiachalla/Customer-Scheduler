package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;



@Service
public class CustomerService {

    @Autowired
    SchedulerService schedulerService;
    
    @Autowired
    CustomerRepository customerRepository;


    // @Autowired
    // public CustomerService(SchedulerService schedulerService, CustomerRepository customerRepository) {
    //     this.schedulerService = schedulerService;
    //     this.customerRepository = customerRepository;
    // }


    public String addCustomer(Customer customer) {
        // store in DB
        // send to scheduler
        
        // if(customer exists, return "customer already exists")

        // Optional<Customer> customerExists = customerRepository.findByName(customer.getName());
        

        // if(customerExists.isPresent()) {
        //     return "Customer already exists!";
        // } 

        customerRepository.save(customer);
        
        schedulerService.checkIn(customer);
        return " customers added!";
    }

    public String getSequenceNumber(Customer customer) {
        // if the customer exists in db, return the ticket number
        
        return schedulerService.getSequenceNumber(customer);
    }
}
