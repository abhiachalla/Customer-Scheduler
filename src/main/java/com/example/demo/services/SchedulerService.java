package com.example.demo.services;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.CustomerType;
import com.example.demo.domain.models.Employee;
import com.example.demo.domain.repositories.CustomerRepository;

@Service
public class SchedulerService {
    
    Queue<Customer> VIPCustomerList;
    Queue<Customer> NormalCustomerList;    

    Queue<Employee> availableEmployees;

    @Autowired
    CustomerRepository customerRepository;

    int vipCount;
    int normalCount;
    int generatedTicketNumber;
    int sequenceNumber;

    public SchedulerService() {
        this.VIPCustomerList = new LinkedList<>();
        this.NormalCustomerList = new LinkedList<>();

        this.vipCount = 0;
        this.normalCount = 0;   
        this.generatedTicketNumber = 1;
        this.sequenceNumber = 1;    

        this.availableEmployees = new LinkedList<>();

        for(int i = 0; i < 0; i++) {
            availableEmployees.add(new Employee("Employee " + i));
        }
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

    public String getgeneratedTicketNumber(Customer customer) {
        return "Your generated ticket number is " + customer.getTicketNumber();
    }

    public Queue<Employee> getAllAvailabeEmployees() {
        return availableEmployees;
    }
}
