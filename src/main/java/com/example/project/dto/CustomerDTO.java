package com.example.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.project.model.Customer.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long customerId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    @NotNull(message = "Status is required")
    private Status status;

    private String createdDate;

    // Method to convert LocalDateTime to formatted String
    public void setCreatedDate(LocalDateTime createdDate) {
        if (createdDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");
            this.createdDate = createdDate.format(formatter);
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
