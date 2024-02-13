package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.CustomerType;
import com.example.demo.models.Employee;
import com.example.demo.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {

    @Mock(lenient = true)
    private CustomerRepository customerRepository;    

    @InjectMocks
    private SchedulerService schedulerService;

    @BeforeEach
    void setUp() {
        // Assuming Employee initialization is corrected, if necessary, for demonstration
        // For example, if you're adding employees to availableEmployees here
    }

    @Test
    void testCheckInVIPCustomer() {
        Customer vipCustomer = new Customer("John Doe", "12345", CustomerType.VIP, "John");
        when(customerRepository.findByName("John")).thenReturn(Optional.of(vipCustomer));

        String response = schedulerService.checkIn(vipCustomer);
        assertTrue(response.contains("Your sequential service number is"));
        assertFalse(schedulerService.VIPCustomerList.isEmpty());
        assertTrue(schedulerService.NormalCustomerList.isEmpty());
    }

    @Test
    void testCheckInNormalCustomer() {
        Customer normalCustomer = new Customer("Jane Doe", "67890", CustomerType.NORMAL, "Jane");
        when(customerRepository.findByName("Jane")).thenReturn(Optional.of(normalCustomer));

        String response = schedulerService.checkIn(normalCustomer);
        assertTrue(response.contains("Your sequential service number is"));
        assertTrue(schedulerService.VIPCustomerList.isEmpty());
        assertFalse(schedulerService.NormalCustomerList.isEmpty());
    }

    @Test
    void testGetNextCustomerVIPFirst() {
        Customer vipCustomer = new Customer("VIP John", "123", CustomerType.VIP, "VIPJohn");
        Customer normalCustomer = new Customer("Normal Jane", "456", CustomerType.NORMAL, "NormalJane");

        schedulerService.availableEmployees.add(new Employee("Employee 1"));
        schedulerService.checkIn(vipCustomer);
        schedulerService.checkIn(normalCustomer);

        Customer nextCustomer = schedulerService.getNextCustomer();
        assertEquals(vipCustomer.getName(), nextCustomer.getName());
    }

    @Test
    void testGetNextCustomerWhenNoVIPs() {
        Customer normalCustomer = new Customer("Normal Jane", "456", CustomerType.NORMAL, "NormalJane");
        schedulerService.availableEmployees.add(new Employee("Employee 1"));
        schedulerService.checkIn(normalCustomer);

        Customer nextCustomer = schedulerService.getNextCustomer();
        assertEquals(normalCustomer.getName(), nextCustomer.getName());
    }
}
