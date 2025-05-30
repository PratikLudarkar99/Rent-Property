package com.example.project.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "dashboard")
@Data
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long totalCustomers;
    private long totalBrokers;
    private long totalProperties;
    private long activeSubscriptions;

    private LocalDateTime lastUpdated;
}
