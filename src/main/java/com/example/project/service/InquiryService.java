package com.example.project.service;

import com.example.project.dto.CustomerDTO;
import com.example.project.dto.BrokerDTO;
import com.example.project.dto.PropertyDTO;
import com.example.project.dto.InquiryDTO;
import com.example.project.model.Broker;
import com.example.project.model.Customer;
import com.example.project.model.Inquiry;
import com.example.project.model.Property;
import com.example.project.repository.BrokerRepository;
import com.example.project.repository.CustomerRepository;
import com.example.project.repository.InquiryRepository;
import com.example.project.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public List<InquiryDTO> getAllInquiriesForAdmin() {
        return inquiryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InquiryDTO createInquiry(InquiryDTO dto) {
        Inquiry inquiry = new Inquiry();
        inquiry.setCreatedDate(LocalDateTime.now());

        if (dto.getCustomer() != null && dto.getCustomer().getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomer().getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            inquiry.setCustomer(customer);
        }

        if (dto.getBroker() != null && dto.getBroker().getBrokerId() != null) {
            Broker broker = brokerRepository.findById(dto.getBroker().getBrokerId())
                    .orElseThrow(() -> new RuntimeException("Broker not found"));
            inquiry.setBroker(broker);
        }

        if (dto.getProperty() != null && dto.getProperty().getPropertyId() != null) {
            Property property = propertyRepository.findById(dto.getProperty().getPropertyId())
                    .orElseThrow(() -> new RuntimeException("Property not found"));
            inquiry.setProperty(property);
        }

        Inquiry saved = inquiryRepository.save(inquiry);
        return convertToDTO(saved);
    }

    public InquiryDTO updateInquiry(Long id, InquiryDTO dto) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setCreatedDate(LocalDateTime.now());

        if (dto.getCustomer() != null && dto.getCustomer().getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomer().getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            inquiry.setCustomer(customer);
        }

        if (dto.getBroker() != null && dto.getBroker().getBrokerId() != null) {
            Broker broker = brokerRepository.findById(dto.getBroker().getBrokerId())
                    .orElseThrow(() -> new RuntimeException("Broker not found"));
            inquiry.setBroker(broker);
        }

        if (dto.getProperty() != null && dto.getProperty().getPropertyId() != null) {
            Property property = propertyRepository.findById(dto.getProperty().getPropertyId())
                    .orElseThrow(() -> new RuntimeException("Property not found"));
            inquiry.setProperty(property);
        }

        Inquiry updated = inquiryRepository.save(inquiry);
        return convertToDTO(updated);
    }

    public void deleteInquiry(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
        inquiryRepository.delete(inquiry);
    }

    private InquiryDTO convertToDTO(Inquiry inquiry) {
        InquiryDTO dto = new InquiryDTO();
        dto.setEnquiryId(inquiry.getEnquiryId());
        dto.setEnquiryDateTime(inquiry.getCreatedDate());

        if (inquiry.getCustomer() != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(inquiry.getCustomer().getCustomerId());
            customerDTO.setFirstName(inquiry.getCustomer().getFirstName());
            customerDTO.setLastName(inquiry.getCustomer().getLastName());
            dto.setCustomer(customerDTO);
        }

        if (inquiry.getBroker() != null) {
            BrokerDTO brokerDTO = new BrokerDTO();
            brokerDTO.setBrokerId(inquiry.getBroker().getBrokerId());
            brokerDTO.setFirstName(inquiry.getBroker().getFirstName());
            brokerDTO.setLastName(inquiry.getBroker().getLastName());
            dto.setBroker(brokerDTO);
        }

        if (inquiry.getProperty() != null) {
            Property property = inquiry.getProperty();
            PropertyDTO propertyDTO = new PropertyDTO();

            propertyDTO.setPropertyId(property.getPropertyId());
            propertyDTO.setTitle(property.getTitle());
            propertyDTO.setCategory(property.getCategory());
            propertyDTO.setPropertyName(property.getPropertyName());
            propertyDTO.setPrice(property.getPrice());
            propertyDTO.setFurnishedType(property.getFurnishedType());
            propertyDTO.setPriceDuration(property.getPriceDuration());
            propertyDTO.setSecurityDeposit(property.getSecurityDeposit());
            propertyDTO.setSquareFeetArea(property.getSquareFeetArea());
            propertyDTO.setDescription(property.getDescription());
            propertyDTO.setSellerType(property.getSellerType());
            propertyDTO.setAgeOfProperty(property.getAgeOfProperty());
            propertyDTO.setMaintenanceCharges(property.getMaintenanceCharges());
            propertyDTO.setBrokerage(property.getBrokerage());
            propertyDTO.setBhk(property.getBhk());
            propertyDTO.setPropertyFor(property.getPropertyFor());
            propertyDTO.setPremiumProperty(property.getPremiumProperty());
            propertyDTO.setStatus(property.getStatus());
            propertyDTO.setCountry(property.getCountry());
            propertyDTO.setState(property.getState());
            propertyDTO.setCity(property.getCity());
            propertyDTO.setLatitude(property.getLatitude());
            propertyDTO.setLongitude(property.getLongitude());
            propertyDTO.setAddress(property.getAddress());
            propertyDTO.setCoverImage(property.getCoverImage());

            propertyDTO.setPropertyGallery(
                    property.getPropertyGallery() != null ?
                            Arrays.asList(property.getPropertyGallery().split(",")) : null
            );

            propertyDTO.setAmenities(
                    property.getAmenities() != null ?
                            Arrays.asList(property.getAmenities().split(",")) : null
            );

            propertyDTO.setVideoUrl(property.getVideoUrl());

            dto.setProperty(propertyDTO);
        }

        return dto;
    }
}
