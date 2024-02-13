package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.SchedulerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @Autowired
    SchedulerService schedulerService;
    // autowire this in employee service only and remove here!!!!!1
    
    @PostMapping("/freeEmployee")
    public void freeEmployee(@RequestBody String employeeName) {
        System.out.println(employeeName + "is the emp name");
        employeeService.freeEmployee(employeeName, schedulerService);
    }

    @GetMapping("/serveNextCustomer")
    public String getMethodName() {

        Customer nextCustomer  = schedulerService.getNextCustomer();

        Employee employee = new Employee("John");
        
        return employee.serveCustomer(nextCustomer);
    }
    

    @PostMapping("/createEmployees")
    public String getMethodName2(@RequestBody String employeeCount) {
        int noOfEmployees = Integer.parseInt(employeeCount);
        employeeService.createEmployees(noOfEmployees, schedulerService);
        return "employees created and added to the store!";
    }
    
    
}
