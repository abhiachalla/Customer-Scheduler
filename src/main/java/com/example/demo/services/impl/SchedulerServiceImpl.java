package com.example.demo.services.impl;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.CustomerType;
import com.example.demo.domain.models.Employee;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.domain.repositories.EmployeeRepository;
import com.example.demo.services.SchedulerService;


@Service
public class SchedulerServiceImpl implements SchedulerService {
    
    private Queue<Customer> VIPCustomerList;
    private Queue<Customer> NormalCustomerList;    

    private Queue<Employee> availableEmployees;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private int vipCount;
    private int normalCount;
    private int generatedTicketNumber;
    private int sequenceNumber;

    public SchedulerServiceImpl() {

        this.VIPCustomerList = new LinkedList<>();
        this.NormalCustomerList = new LinkedList<>();

        this.vipCount = 0;
        this.normalCount = 0;   
        this.generatedTicketNumber = 1;
        this.sequenceNumber = 1;    

        this.availableEmployees = new LinkedList<>();
    }

    public String checkIn(Customer customer) {

        if (customer.getCustomerType().equals(CustomerType.VIP)) {
            VIPCustomerList.add(customer);
        } else {
            NormalCustomerList.add(customer);
        }
        int sequentialServiceNumber = sequenceNumber++;
        
        this.updateSequenceNumber(customer.getName(), sequentialServiceNumber);

        return "Your sequential service number is " + sequentialServiceNumber;
    }

    public Customer getNextCustomer() {

        if(availableEmployees.isEmpty()) {
            return new Customer("", "", CustomerType.WAITING, "");
        }

        if(vipCount < 2 && !VIPCustomerList.isEmpty()) {

            vipCount++;

            int ticketID = generatedTicketNumber++;
            
            Customer nexCustomer =  VIPCustomerList.poll();
            int nextSequentialServiceNumber = nexCustomer.getSequentialServiceNumber();

            this.updateCustomerTicketNumber(nextSequentialServiceNumber, ticketID);

            this.assignCustomerToEmployee(nextSequentialServiceNumber);
            
            return nexCustomer;
        } 
        else if(!NormalCustomerList.isEmpty()) {

            normalCount++;
            
            vipCount = 0;
            normalCount = 0;
            
            int ticketID = generatedTicketNumber++;
            
            Customer nexCustomer = NormalCustomerList.poll();
            int nextSequentialServiceNumber = nexCustomer.getSequentialServiceNumber();

            this.updateCustomerTicketNumber(nextSequentialServiceNumber, ticketID);

            this.assignCustomerToEmployee(nextSequentialServiceNumber);
            
            return nexCustomer;
        }
        
        return new Customer("", "", CustomerType.EMPTY, "");
    }

    private void updateCustomerTicketNumber(int nextSequentialServiceNumber, int ticketID) {

        Optional<Customer> customerFromDB = customerRepository.findBySequentialServiceNumber(nextSequentialServiceNumber);

            if(customerFromDB.isPresent()) {
                customerFromDB.get().setTicketNumber(ticketID);
                customerRepository.save(customerFromDB.get());
            }
    }

    private void updateSequenceNumber(String nextCustomerName, int sequenceNumber) {

        Optional<Customer> customerFromDB = customerRepository.findByName(nextCustomerName);

            if(customerFromDB.isPresent()) {
                customerFromDB.get().setSequentialServiceNumber(sequenceNumber);
                customerRepository.save(customerFromDB.get());
            }
    }

    private void assignCustomerToEmployee(int nextSequentialServiceNumber) {
        
        Employee servingEmployee = availableEmployees.poll();

        servingEmployee.setSequentialServiceNumberServing(nextSequentialServiceNumber);

        String servingEmployeeName = servingEmployee.getName();

        employeeRepository.updateSequenceNumberByName(servingEmployeeName, nextSequentialServiceNumber);
    }

    public Queue<Employee> getAllAvailabeEmployees() {
        return availableEmployees;
    }

    public void createEmployees(int noOfEmployees) {

        for(int i = 0; i < noOfEmployees; i++) {
            
            Employee employee = new Employee("Employee " + i);
            availableEmployees.add(employee);
            employeeRepository.save(employee);
        }
    }

    public void freeEmployee(String name) {

        Employee employee = employeeRepository.findByName(name);

        // Add the employee back the available employees list to make him available for the next customer.
        availableEmployees.add(employee);

        employee.setSequentialServiceNumberServing(0);

        employeeRepository.save(employee);
    }


}
