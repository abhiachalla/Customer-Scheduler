package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.models.Customer;
import com.example.demo.domain.models.CustomerType;
import com.example.demo.services.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private SchedulerService schedulerService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testFreeEmployee() {

        String employeeName = "John Doe";

        employeeService.freeEmployee(employeeName, schedulerService);

        verify(schedulerService, times(1)).freeEmployee(employeeName);
    }

    @Test
    void testCreateEmployees() {

        String employeeCount = "5";

        employeeService.createEmployees(employeeCount, schedulerService);

        verify(schedulerService, times(1)).createEmployees(Integer.parseInt(employeeCount));
    }

    @Test
    void testServeNextCustomer() {

        Customer nextCustomer = new Customer("Jane Doe", "1234567890", CustomerType.NORMAL, "No Requests");
        when(schedulerService.getNextCustomer()).thenReturn(nextCustomer);

        String result = employeeService.serveNextCustomer(schedulerService);

        assertNotNull(result);
        assertTrue(!result.contains("John")); 
    }
}
