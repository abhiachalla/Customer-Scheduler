package com.example.demo.services;

import com.example.demo.domain.models.Employee;
import com.example.demo.services.impl.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private SchedulerService schedulerService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Queue<Employee> mockQueue;

    @BeforeEach
    void setUp() {
        mockQueue = new LinkedList<>();
        when(schedulerService.getAllAvailabeEmployees()).thenReturn(mockQueue);
    }

    @Test
    void testFreeEmployeeAddsEmployeeWhenQueueIsNotFull() {
        for (int i = 0; i < 9; i++) { // Fill the queue with 9 employees to simulate it being not full
            mockQueue.add(new Employee("Employee " + i));
        }
        
        employeeService.freeEmployee("10", schedulerService);
        
        assertEquals(10, mockQueue.size(), "Queue should have 10 employees after adding a new one");
    }

    @Test
    void testFreeEmployeeDoesNotAddEmployeeWhenQueueIsFull() {
        for (int i = 0; i < 10; i++) { // Fill the queue with 10 employees to simulate it being full
            mockQueue.add(new Employee("Employee " + i));
        }
        
        employeeService.freeEmployee("11", schedulerService);
        
        assertEquals(10, mockQueue.size(), "Queue should remain full with 10 employees and not add a new one");
    }
}
