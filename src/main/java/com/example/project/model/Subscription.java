package com.example.project.model;

import com.example.project.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id", nullable = false)
    private Package aPackage;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false, length = 50)
    private String paymentType; // Example: "UPI", "CARD", "CASH"

    @Column(nullable = false)
    private LocalDate subscriptionStartDate;

    @Column(nullable = false)
    private LocalDate subscriptionEndDate;

    @Column
    private LocalDate cancelledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status; // ACTIVE / INACTIVE
}
