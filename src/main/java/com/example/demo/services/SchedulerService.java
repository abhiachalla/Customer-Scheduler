package com.example.demo.services;

import java.util.Queue;
import org.springframework.stereotype.Service;
import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.Employee;

@Service
public interface SchedulerService {

    public String checkIn(Customer customer);
    
    public Customer getNextCustomer();
    
    public Queue<Employee> getAllAvailabeEmployees();

    public void createEmployees(int noOfEmployees);

    public void freeEmployee(String name);
} 
