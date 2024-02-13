package com.example.demo.models;

public class Employee {
        
    private String name;

    private boolean isAvailable;

    Customer currentServingCustomer;

    public Employee(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public String serveCustomer(Customer customer) {

        if(customer.getCustomerType() == CustomerType.EMPTY) {
            return "No customers to serve";
        }

        if(customer.getCustomerType() == CustomerType.WAITING) {
            return "No available employees to serve the customer, please wait!!!";
        }
        
        this.currentServingCustomer = customer;
        this.isAvailable = false;
        
        String customerRequests = customer.getRequests();
        StringBuilder employeeResponses = new StringBuilder();

        for (String request : customerRequests.split(",")) {
            employeeResponses.append("Request ******" + request + "****** completed, ");
        }
        
        return employeeResponses.toString();
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public String getName() {
        return this.name;
    }


}
