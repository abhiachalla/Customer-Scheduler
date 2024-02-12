package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {

    @Test
    public void testNoArgsConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer, "Customer should be not null");
    }

    @Test
    public void testAllArgsConstructor() {
        Customer customer = new Customer("John Doe", "1234567890", CustomerType.VIP, "Special requests");
        assertEquals("John Doe", customer.getName(), "Name should match constructor argument");
        assertEquals("1234567890", customer.getNumber(), "Number should match constructor argument");
        assertEquals(CustomerType.VIP, customer.getCustomerType(), "CustomerType should match constructor argument");
        assertEquals("Special requests", customer.getRequests(), "Requests should match constructor argument");
    }

    @Test
    public void testSettersAndGetters() {
        Customer customer = new Customer();

        customer.setName("Jane Doe");
        assertEquals("Jane Doe", customer.getName(), "Name should be set and retrieved correctly");

        customer.setNumber("0987654321");
        assertEquals("0987654321", customer.getNumber(), "Number should be set and retrieved correctly");

        customer.setCustomerType(CustomerType.NORMAL);
        assertEquals(CustomerType.NORMAL, customer.getCustomerType(), "CustomerType should be set and retrieved correctly");

        customer.setRequests("No special requests");
        assertEquals("No special requests", customer.getRequests(), "Requests should be set and retrieved correctly");

        customer.setSequentialServiceNumber(1);
        assertEquals(1, customer.getSequentialServiceNumber(), "SequentialServiceNumber should be set and retrieved correctly");

        customer.setTicketNumber(100);
        assertEquals(100, customer.getTicketNumber(), "TicketNumber should be set and retrieved correctly");
    }
}
