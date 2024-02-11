package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.SchedulerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @Autowired
    SchedulerService schedulerService;
    
    @GetMapping("/freeEmployee")
    public void freeEmployee(@RequestBody int eID) {
        employeeService.freeEmployee(eID);
    }

    @GetMapping("/serveNextCustomer")
    public String getMethodName() {
        // Scheduler.getNextCustomer();
        Customer nextCustomer  = schedulerService.getNextCustomer();
        Employee employee = new Employee("John");
        return employee.serveCustomer(nextCustomer);
    }
    
    
}
