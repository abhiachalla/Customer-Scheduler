package com.example.demo.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.services.impl.CustomerServiceImpl;

class CustomerServiceTest {

    @Mock
    private SchedulerService schedulerService;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomerTest() {
        // Arrange
        Customer customer = new Customer(); // Assuming Customer class has a parameterless constructor
        String expectedResponse = "Success";
        
        when(schedulerService.checkIn(any(Customer.class))).thenReturn(expectedResponse);

        // Act
        String actualResponse = customerService.addCustomer(customer);
        System.out.println(actualResponse);

        // Assert
        verify(customerRepository, times(1)).save(customer);
        verify(schedulerService, times(1)).checkIn(customer);
        assertEquals(expectedResponse, actualResponse, "The response from schedulerService.checkIn should be returned.");
    }
}
