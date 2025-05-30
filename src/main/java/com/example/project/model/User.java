package com.example.project.model;

import com.example.project.enums.CustomerStatus;
import com.example.project.enums.Role;
import com.example.project.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Common Fields
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // CUSTOMER / BROKER / ADMIN

    @Column(nullable = false)
    private Boolean active = true;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d, yyyy h:mm a")
    @Column(nullable = false)
    private LocalDateTime createdDate;

    // Broker-specific fields
    private String agencyName;

    private Integer yearsOfExperience;

    @Column(columnDefinition = "TEXT")
    private String aboutYou;

    @Lob
    private byte[] profileImage;

    // Customer-specific fields
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
