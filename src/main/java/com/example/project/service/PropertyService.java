package com.example.project.service;

import com.example.project.dto.PropertyDTO;
import com.example.project.model.Property;
import com.example.project.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    private String listToString(List<String> list) {
        return list != null ? String.join(",", list) : null;
    }

    private List<String> stringToList(String str) {
        return str != null && !str.isEmpty() ? Arrays.asList(str.split(",")) : Collections.emptyList();
    }

    public PropertyDTO saveProperty(PropertyDTO dto) {
        Property property = Property.builder()
                .category(dto.getCategory())
                .propertyName(dto.getPropertyName())
                .price(dto.getPrice())
                .furnishedType(dto.getFurnishedType())
                .priceDuration(dto.getPriceDuration())
                .securityDeposit(dto.getSecurityDeposit())
                .squareFeetArea(dto.getSquareFeetArea())
                .description(dto.getDescription())
                .sellerType(dto.getSellerType())
                .ageOfProperty(dto.getAgeOfProperty())
                .maintenanceCharges(dto.getMaintenanceCharges())
                .brokerage(dto.getBrokerage())
                .bhk(dto.getBhk())
                .propertyFor(dto.getPropertyFor())
                .premiumProperty(dto.getPremiumProperty())
                .status(dto.getStatus())
                .country(dto.getCountry())
                .state(dto.getState())
                .city(dto.getCity())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .address(dto.getAddress())
                .coverImage(dto.getCoverImage())
                .propertyGallery(listToString(dto.getPropertyGallery()))
                .videoUrl(dto.getVideoUrl())
                .amenities(listToString(dto.getAmenities()))
                .build();

        property = propertyRepository.save(property);

        dto.setPropertyId(property.getPropertyId());  // changed here
        return dto;
    }

    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream().map(property ->
                PropertyDTO.builder()
                        .propertyId(property.getPropertyId())  // changed here
                        .category(property.getCategory())
                        .propertyName(property.getPropertyName())
                        .price(property.getPrice())
                        .furnishedType(property.getFurnishedType())
                        .priceDuration(property.getPriceDuration())
                        .securityDeposit(property.getSecurityDeposit())
                        .squareFeetArea(property.getSquareFeetArea())
                        .description(property.getDescription())
                        .sellerType(property.getSellerType())
                        .ageOfProperty(property.getAgeOfProperty())
                        .maintenanceCharges(property.getMaintenanceCharges())
                        .brokerage(property.getBrokerage())
                        .bhk(property.getBhk())
                        .propertyFor(property.getPropertyFor())
                        .premiumProperty(property.getPremiumProperty())
                        .status(property.getStatus())
                        .country(property.getCountry())
                        .state(property.getState())
                        .city(property.getCity())
                        .latitude(property.getLatitude())
                        .longitude(property.getLongitude())
                        .address(property.getAddress())
                        .coverImage(property.getCoverImage())
                        .propertyGallery(stringToList(property.getPropertyGallery()))
                        .videoUrl(property.getVideoUrl())
                        .amenities(stringToList(property.getAmenities()))
                        .build()
        ).collect(Collectors.toList());
    }

    public PropertyDTO getPropertyById(Long id) {
        return propertyRepository.findById(id).map(property ->
                PropertyDTO.builder()
                        .propertyId(property.getPropertyId())  // changed here
                        .category(property.getCategory())
                        .propertyName(property.getPropertyName())
                        .price(property.getPrice())
                        .furnishedType(property.getFurnishedType())
                        .priceDuration(property.getPriceDuration())
                        .securityDeposit(property.getSecurityDeposit())
                        .squareFeetArea(property.getSquareFeetArea())
                        .description(property.getDescription())
                        .sellerType(property.getSellerType())
                        .ageOfProperty(property.getAgeOfProperty())
                        .maintenanceCharges(property.getMaintenanceCharges())
                        .brokerage(property.getBrokerage())
                        .bhk(property.getBhk())
                        .propertyFor(property.getPropertyFor())
                        .premiumProperty(property.getPremiumProperty())
                        .status(property.getStatus())
                        .country(property.getCountry())
                        .state(property.getState())
                        .city(property.getCity())
                        .latitude(property.getLatitude())
                        .longitude(property.getLongitude())
                        .address(property.getAddress())
                        .coverImage(property.getCoverImage())
                        .propertyGallery(stringToList(property.getPropertyGallery()))
                        .videoUrl(property.getVideoUrl())
                        .amenities(stringToList(property.getAmenities()))
                        .build()
        ).orElse(null);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
