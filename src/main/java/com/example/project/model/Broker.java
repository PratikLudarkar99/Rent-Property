package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "brokers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"contact_number"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Broker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_id", nullable = false, updatable = false)
    private Long brokerId;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Contact number is required")
    @Column(name = "contact_number", nullable = false, unique = true)
    private String contactNumber;

    @NotBlank(message = "Agency name is required")
    @Column(name = "agency_name", nullable = false)
    private String agencyName;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience;

    @NotBlank(message = "About you is required")
    @Column(name = "about_you", nullable = false, columnDefinition = "TEXT")
    private String aboutYou;

    @Lob
    @Column(name = "profile_image", nullable = false)
    private byte[] profileImage;
}
