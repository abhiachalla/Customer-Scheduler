package com.example.demo.services;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.models.CustomerType;
import com.example.demo.repositories.CustomerRepository;

@Service
public class SchedulerService {
    
    Queue<Customer> VIPCustomerList;
    Queue<Customer> NormalCustomerList;    

    @Autowired
    CustomerRepository customerRepository;

    int vipCount;
    int normalCount;
    int sequenceNumber;

    public SchedulerService() {
        this.VIPCustomerList = new LinkedList<>();
        this.NormalCustomerList = new LinkedList<>();

        this.vipCount = 0;
        this.normalCount = 0;   
        this.sequenceNumber = 1;
    }

    public void checkIn(Customer customer) {

        if (customer.getCustomerType().equals(CustomerType.VIP)) {
            VIPCustomerList.add(customer);
        } else {
            NormalCustomerList.add(customer);
        }
    }

    public Customer getNextCustomer() {

        if(vipCount < 2 && !VIPCustomerList.isEmpty()) {

            vipCount++;

            int ticketID = sequenceNumber++;
            
            Customer nexCustomer =  VIPCustomerList.poll();
            String nextCustomerName = nexCustomer.getName();

            this.updateCustomerTicketNumber(nextCustomerName, ticketID);
            
            return nexCustomer;
        } 
        else if(!NormalCustomerList.isEmpty()) {

            normalCount++;
            
            vipCount = 0;
            normalCount = 0;
            
            int ticketID = sequenceNumber++;
            
            Customer nexCustomer = NormalCustomerList.poll();
            String nextCustomerName = nexCustomer.getName();

            this.updateCustomerTicketNumber(nextCustomerName, ticketID);
            
            return nexCustomer;
        }
        
        return new Customer("", "", CustomerType.EMPTY, "");
    }

    private void updateCustomerTicketNumber(String nextCustomerName, int ticketID) {

        Optional<Customer> customerFromDB = customerRepository.findByName(nextCustomerName);

            if(customerFromDB.isPresent()) {
                customerFromDB.get().setTicketNumber(ticketID);
                customerRepository.save(customerFromDB.get());
            }
    }

    public String getSequenceNumber(Customer customer) {
        return "Your sequence number is " + customer.getTicketNumber();
    }
}
