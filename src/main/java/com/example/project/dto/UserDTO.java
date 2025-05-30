package com.example.project.dto;

import com.example.project.enums.CustomerStatus;
import com.example.project.enums.Role;
import com.example.project.enums.Status;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private Role role;
    private Boolean active;
    private LocalDateTime createdDate;

    // Broker-specific
    private String agencyName;
    private Integer yearsOfExperience;
    private String aboutYou;
    private MultipartFile profileImage;

    // Customer-specific
    private CustomerStatus customerStatus;
}
