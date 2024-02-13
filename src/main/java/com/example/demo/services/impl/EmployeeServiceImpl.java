package com.example.demo.services.impl;

import org.springframework.stereotype.Service;
import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.SchedulerService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Override
    public void freeEmployee(String name, SchedulerService schedulerService) {
        
        schedulerService.freeEmployee(name);
    }

    @Override
    public void createEmployees(String employeeCount, SchedulerService schedulerService) {
        
        int noOfEmployees = Integer.parseInt(employeeCount);
        
        schedulerService.createEmployees(noOfEmployees);
    }

    @Override
    public String serveNextCustomer(SchedulerService schedulerService) {
        Customer nextCustomer  = schedulerService.getNextCustomer();

        Employee employee = new Employee("John");
        
        return employee.serveCustomer(nextCustomer);
    }
}
