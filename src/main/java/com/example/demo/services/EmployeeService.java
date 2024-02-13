package com.example.demo.services;

import java.util.Queue;

import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;

@Service
public class EmployeeService {

    public void freeEmployee(String name, SchedulerService schedulerService) {

        Queue<Employee> availableEmployees = schedulerService.getAllAvailabeEmployees();

        if(availableEmployees.size() == 10) return;
        
        availableEmployees.add(new Employee("Employee " + name));
    }
}
