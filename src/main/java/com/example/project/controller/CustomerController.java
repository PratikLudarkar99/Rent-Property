package com.example.project.controller;

import com.example.project.dto.CustomerDTO;
import com.example.project.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get all customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // Get a customer by ID
    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String customerId) {
        Long id = Long.parseLong(customerId);
        return ResponseEntity.ok(customerService.getCustomerByCustomerId(id));
    }

    // Create a new customer with validation
    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomerId(null); // Ensure ID is not passed during creation
        return ResponseEntity.status(201).body(customerService.createCustomer(customerDTO));
    }

    // Update an existing customer
    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String customerId, @RequestBody CustomerDTO customerDTO) {
        Long id = Long.parseLong(customerId);
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }

    // Delete a customer by ID
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Log the error
            System.err.println("Error deleting customer: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    // Update customer status
    @PutMapping("/{customerId}/status")
    public ResponseEntity<CustomerDTO> updateCustomerStatus(
            @PathVariable String customerId,
            @RequestBody Map<String, String> statusRequest) {
        String newStatus = statusRequest.get("status");
        Long id = Long.parseLong(customerId);
        CustomerDTO updatedCustomer = customerService.updateCustomerStatus(id, newStatus);
        return ResponseEntity.ok(updatedCustomer);
    }
}
