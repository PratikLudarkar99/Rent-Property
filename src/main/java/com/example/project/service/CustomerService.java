package com.example.project.service;

import com.example.project.dto.CustomerDTO;
import com.example.project.model.Customer;
import com.example.project.repository.CustomerRepository;
import com.example.project.repository.InquiryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InquiryRepository inquiryRepository; // ✅ Added

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        return convertToDTO(customer);
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (customerRepository.existsByContactNumber(dto.getContactNumber())) {
            throw new RuntimeException("Contact number already exists");
        }

        Customer customer = convertToEntity(dto);
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        if (!customer.getEmail().equals(dto.getEmail()) &&
                customerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (!customer.getContactNumber().equals(dto.getContactNumber()) &&
                customerRepository.existsByContactNumber(dto.getContactNumber())) {
            throw new RuntimeException("Contact number already exists");
        }

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setContactNumber(dto.getContactNumber());
        customer.setStatus(dto.getStatus() != null ? dto.getStatus() : Customer.Status.ACTIVE);

        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        try {
            // Delete inquiries associated with the customer
            inquiryRepository.deleteByCustomerId(customerId);

            // Delete the customer
            customerRepository.deleteById(customerId);
        } catch (Exception e) {
            // Log the error
            System.err.println("Failed to delete customer with ID: " + customerId + ". Error: " + e.getMessage());
            throw new RuntimeException("Failed to delete customer with ID: " + customerId, e);
        }
    }

    public CustomerDTO updateCustomerStatus(Long customerId, String newStatus) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        try {
            Customer.Status statusEnum = Customer.Status.valueOf(newStatus.toUpperCase());
            customer.setStatus(statusEnum);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value: " + newStatus);
        }

        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setContactNumber(customer.getContactNumber());
        dto.setStatus(customer.getStatus());

        // ✅ Format createdDate as "May 29, 2025 3:15 PM"
        if (customer.getCreatedDate() != null) {
            dto.setCreatedDate(customer.getCreatedDate());
        }

        return dto;
    }

    private Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setContactNumber(dto.getContactNumber());
        customer.setStatus(dto.getStatus() != null ? dto.getStatus() : Customer.Status.ACTIVE);
        return customer;
    }
}
