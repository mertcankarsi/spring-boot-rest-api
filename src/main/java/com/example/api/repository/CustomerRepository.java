package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
} 