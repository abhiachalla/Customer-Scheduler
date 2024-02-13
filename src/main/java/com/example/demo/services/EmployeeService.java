package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public void freeEmployee(String name, SchedulerService schedulerService);
    
} 
