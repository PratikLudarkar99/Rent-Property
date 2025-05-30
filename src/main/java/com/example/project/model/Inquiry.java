package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inquiries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enquiryId;

    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Broker broker;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @NotNull(message = "Property is required for inquiry")
    private Property property;
}
