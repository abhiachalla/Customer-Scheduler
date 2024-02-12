package com.example.demo.repositories;

import com.example.demo.models.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    Optional<Customer> findByName(String name);
}
