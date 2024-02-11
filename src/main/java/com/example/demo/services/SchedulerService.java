package com.example.demo.services;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.models.CustomerType;

@Service
public class SchedulerService {
    
    Queue<Customer> VIPCustomerList;
    Queue<Customer> NormalCustomerList;    

    int vipCount;
    int normalCount;

    public SchedulerService() {
        this.VIPCustomerList = new LinkedList<>();
        this.NormalCustomerList = new LinkedList<>();

        this.vipCount = 0;
        this.normalCount = 0;   
    }

    public void checkIn(Customer customer) {

        if (customer.getCustomerType().equals(CustomerType.VIP)) {
            VIPCustomerList.add(customer);
        } else {
            NormalCustomerList.add(customer);
        }

        // return Ticket Here
    }

    public Customer getNextCustomer() {

        if(vipCount < 2 && !VIPCustomerList.isEmpty()) {
            vipCount++;
            return VIPCustomerList.poll();
        } else if(!NormalCustomerList.isEmpty()) {
            normalCount++;
            vipCount = 0;
            normalCount = 0;
            return NormalCustomerList.poll();
        }

        return new Customer("", "", CustomerType.EMPTY, "");
    }

}







// 1.) Scheduler should decide the order of customers to be served.
// 2.) Employee APIs for serving customers.