package com.example.demo.services.impl;

import java.util.Queue;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.SchedulerService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    public void freeEmployee(String name, SchedulerService schedulerService) {

        Queue<Employee> availableEmployees = schedulerService.getAllAvailabeEmployees();

        if(availableEmployees.size() == 10) return;
        
        availableEmployees.add(new Employee("Employee " + name));
    }
}
