package com.example.demo.domain.repositories;

import java.util.Queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.models.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.sequentialServiceNumberServing = :sequenceNumber WHERE e.name = :servingEmployeeName")
    void updateSequenceNumberByName(@Param("servingEmployeeName") String servingEmployeeName, @Param("sequenceNumber") int sequenceNumber);

    Employee findByName(String name);
    
}
