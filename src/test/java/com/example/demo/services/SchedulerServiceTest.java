package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.CustomerType;
import com.example.demo.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SchedulerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private SchedulerService schedulerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkInVIPCustomer() {
        Customer vipCustomer = new Customer("John Doe", "1234567890", CustomerType.VIP, "1");
        when(customerRepository.findByName(anyString())).thenReturn(Optional.of(vipCustomer));

        String response = schedulerService.checkIn(vipCustomer);

        verify(customerRepository, times(1)).findByName(vipCustomer.getName());
        verify(customerRepository, times(1)).save(vipCustomer);
        assertTrue(response.contains("Your sequential service number is"));
    }

    @Test
    void checkInNormalCustomer() {
        Customer normalCustomer = new Customer("Jane Doe", "0987654321", CustomerType.NORMAL, "2");
        when(customerRepository.findByName(anyString())).thenReturn(Optional.of(normalCustomer));

        String response = schedulerService.checkIn(normalCustomer);

        verify(customerRepository, times(1)).findByName(normalCustomer.getName());
        verify(customerRepository, times(1)).save(normalCustomer);
        assertTrue(response.contains("Your sequential service number is"));
    }

    @Test
    void getNextCustomerTest() {
        Customer vipCustomer = new Customer("John Doe", "1234567890", CustomerType.VIP, "1");
        Customer normalCustomer = new Customer("Jane Doe", "0987654321", CustomerType.NORMAL, "2");

        schedulerService.checkIn(vipCustomer);
        schedulerService.checkIn(normalCustomer);

        when(customerRepository.findBySequentialServiceNumber(anyInt())).thenReturn(Optional.of(vipCustomer)).thenReturn(Optional.of(normalCustomer));

        Customer nextCustomer = schedulerService.getNextCustomer();

        assertNotNull(nextCustomer);
        verify(customerRepository, times(1)).findBySequentialServiceNumber(anyInt());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getGeneratedTicketNumberTest() {
        Customer customer = new Customer("John Doe", "1234567890", CustomerType.VIP, "1");
        customer.setTicketNumber(100);

        String response = schedulerService.getgeneratedTicketNumber(customer);

        assertEquals("Your generated ticket number is 100", response);
    }
}
