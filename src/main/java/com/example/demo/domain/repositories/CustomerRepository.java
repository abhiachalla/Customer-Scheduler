package com.example.demo.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    Optional<Customer> findByName(String name);

    Optional<Customer> findBySequentialServiceNumber(int sequentialServiceNumber);
}
