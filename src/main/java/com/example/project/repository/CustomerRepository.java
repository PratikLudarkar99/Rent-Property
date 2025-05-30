package com.example.project.repository;

import com.example.project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Not required, but okay if you prefer named clarity
    Optional<Customer> findByCustomerId(long customerId);

    boolean existsByCustomerId(long customerId);

    void deleteByCustomerId(long customerId);

    boolean existsByEmail(String email);

    boolean existsByContactNumber(String contactNumber);
}
