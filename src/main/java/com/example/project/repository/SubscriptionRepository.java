package com.example.project.repository;

import com.example.project.enums.Status;
import com.example.project.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // Count subscriptions with status ACTIVE
    long countByStatus(Status status);
}
