package com.example.project.controller;

import com.example.project.dto.BrokerDTO;
import com.example.project.service.BrokerService;
import com.example.project.service.BrokerServiceOtp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class BrokerController {

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private BrokerServiceOtp brokerServiceOtp;

    @Autowired
    private ObjectMapper objectMapper;

    // Register broker with image upload
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerBroker(
            @RequestPart("broker") String brokerJson,
            @RequestPart("profileImage") MultipartFile profileImage
    ) {
        try {
            BrokerDTO brokerDTO = objectMapper.readValue(brokerJson, BrokerDTO.class);

            // Updated validation logic
            if (brokerDTO.getFirstName() == null || brokerDTO.getFirstName().isBlank() ||
                    brokerDTO.getLastName() == null || brokerDTO.getLastName().isBlank() ||
                    brokerDTO.getEmail() == null || brokerDTO.getEmail().isBlank() ||
                    brokerDTO.getContactNumber() == null || brokerDTO.getContactNumber().isBlank() ||
                    brokerDTO.getAgencyName() == null || brokerDTO.getAgencyName().isBlank() ||
                    brokerDTO.getYearsOfExperience() == null || brokerDTO.getYearsOfExperience() <= 0 ||
                    brokerDTO.getAboutYou() == null || brokerDTO.getAboutYou().isBlank()) {
                return ResponseEntity.badRequest().body("All fields are required and years of experience must be a positive number.");
            }

            BrokerDTO saved = brokerService.registerBroker(brokerDTO, profileImage);
            return ResponseEntity.ok(saved);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Generate OTP
    @PostMapping("/get-otp")
    public ResponseEntity<?> getOtp(@RequestParam String contactNumber) {
        String otp = brokerServiceOtp.generateOtp(contactNumber);
        return ResponseEntity.ok("OTP sent to " + contactNumber + ": " + otp);
    }

    // Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String contactNumber, @RequestParam String otp) {
        boolean isValid = brokerServiceOtp.verifyOtp(contactNumber, otp);
        if (isValid) {
            brokerServiceOtp.clearOtp(contactNumber);
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }

    // Get all brokers
    @GetMapping("/brokers")
    public ResponseEntity<List<BrokerDTO>> getAllBrokers() {
        List<BrokerDTO> brokers = brokerService.getAllBrokers();
        return ResponseEntity.ok(brokers);
    }

    // Handle all generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error: " + e.getMessage());
    }

    // Handle method not allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("HTTP method not allowed: " + ex.getMethod() + ". Use the correct method.");
    }
}
