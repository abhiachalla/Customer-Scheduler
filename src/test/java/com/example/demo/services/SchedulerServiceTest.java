package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.CustomerType;

import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.domain.repositories.EmployeeRepository;
import com.example.demo.services.impl.SchedulerServiceImpl;


@ExtendWith(MockitoExtension.class)
class SchedulerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private SchedulerServiceImpl schedulerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckIn() {

        Customer customer = new Customer("John Doe", "1234567890", CustomerType.NORMAL, "No Requests");

        String response = schedulerService.checkIn(customer);

        assertTrue(response.startsWith("Your sequential service number is"));
    }

    @Test
    void testGetNextCustomerWithNoAvailableEmployees() {

        Customer nextCustomer = schedulerService.getNextCustomer();

        // Then
        assertEquals(CustomerType.WAITING, nextCustomer.getCustomerType());
    }

    @Test
    void testCreateEmployees() {

        int noOfEmployees = 5;

        schedulerService.createEmployees(noOfEmployees);

        assertEquals(noOfEmployees, schedulerService.getAllAvailabeEmployees().size());
    }
}
