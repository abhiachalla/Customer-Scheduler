package com.example.demo.services;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.CustomerType;
import com.example.demo.domain.models.Employee;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.services.impl.SchedulerServiceImpl;

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
    private SchedulerServiceImpl schedulerService;

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
    }

    @Test
    void testCheckInNormalCustomer() {
        Customer normalCustomer = new Customer("Jane Doe", "67890", CustomerType.NORMAL, "Jane");
        when(customerRepository.findByName("Jane")).thenReturn(Optional.of(normalCustomer));

        String response = schedulerService.checkIn(normalCustomer);
        assertTrue(response.contains("Your sequential service number is"));
    }

    @Test
    void testGetNextCustomerVIPFirst() {
        Customer vipCustomer = new Customer("VIP John", "123", CustomerType.VIP, "VIPJohn");
        Customer normalCustomer = new Customer("Normal Jane", "456", CustomerType.NORMAL, "NormalJane");

        schedulerService.getAllAvailabeEmployees().add(new Employee("Employee 1"));
        schedulerService.checkIn(vipCustomer);
        schedulerService.checkIn(normalCustomer);

        Customer nextCustomer = schedulerService.getNextCustomer();
        assertEquals(vipCustomer.getName(), nextCustomer.getName());
    }

    @Test
    void testGetNextCustomerWhenNoVIPs() {
        Customer normalCustomer = new Customer("Normal Jane", "456", CustomerType.NORMAL, "NormalJane");
        schedulerService.getAllAvailabeEmployees().add(new Employee("Employee 1"));
        schedulerService.checkIn(normalCustomer);

        Customer nextCustomer = schedulerService.getNextCustomer();
        assertEquals(normalCustomer.getName(), nextCustomer.getName());
    }
}
