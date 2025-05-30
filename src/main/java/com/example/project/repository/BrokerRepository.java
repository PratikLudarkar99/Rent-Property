package com.example.project.repository;

import com.example.project.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrokerRepository extends JpaRepository<Broker, Long> {

    Optional<Broker> findByEmail(String email);
    Optional<Broker> findByContactNumber(String ContactNumber);
}
