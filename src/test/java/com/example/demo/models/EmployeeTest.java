package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.CustomerType;
import com.example.demo.domain.models.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John Doe");
    }

    @Test
    void testServeCustomerWithEmptyCustomerType() {
        // Assume we can create a Customer instance directly
        Customer emptyCustomer = new Customer("", "", CustomerType.EMPTY, "");
        String response = employee.serveCustomer(emptyCustomer);
        
        assertEquals("No customers to serve", response, "Employee should return no customers to serve for EMPTY customer type");
        assertTrue(employee.getIsAvailable(), "Employee should remain available after attempting to serve an EMPTY customer");
    }

    @Test
    void testServeCustomerWithNormalCustomerType() {
        // Assume we can create a Customer instance directly
        Customer normalCustomer = new Customer("Jane Doe", "1234567890", CustomerType.NORMAL, "Request1,Request2");
        String response = employee.serveCustomer(normalCustomer);
        
        assertFalse(employee.getIsAvailable(), "Employee should be marked as not available after serving a customer");
        assertTrue(response.contains("Request ******Request1****** completed"), "Response should contain completion text for Request1");
        assertTrue(response.contains("Request ******Request2****** completed"), "Response should contain completion text for Request2");
    }
}
