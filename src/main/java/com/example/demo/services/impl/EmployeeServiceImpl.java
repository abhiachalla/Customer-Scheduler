package com.example.demo.services.impl;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Employee;
import com.example.demo.domain.repositories.EmployeeRepository;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.SchedulerService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    public void freeEmployee(String name, SchedulerService schedulerService) {
        
        schedulerService.freeEmployee(name);
    }

    public void createEmployees(int noOfEmployees, SchedulerService schedulerService) {
        
        schedulerService.createEmployees(noOfEmployees);
    }
}
