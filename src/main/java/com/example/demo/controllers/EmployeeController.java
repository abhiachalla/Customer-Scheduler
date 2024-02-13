package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @Autowired
    SchedulerService schedulerService;
    
    @PostMapping("/freeEmployee")
    public void freeEmployee(@RequestBody String employeeName) {

        employeeService.freeEmployee(employeeName, schedulerService);
    }

    @GetMapping("/serveNextCustomer")
    public String getMethodName() {

        return employeeService.serveNextCustomer(schedulerService);
    }
    

    @PostMapping("/createEmployees")
    public String getMethodName2(@RequestBody String employeeCount) {
        
        employeeService.createEmployees(employeeCount, schedulerService);
        return "employees created and added to the store!";
    }
    
    
}
