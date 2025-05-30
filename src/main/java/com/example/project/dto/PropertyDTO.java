package com.example.project.dto;

import com.example.project.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {

    
    private Long propertyId; // Newly added
    private String title;    // Newly added

    private PropertyCategory category;
    private String propertyName;
    private BigDecimal price;
    private FurnishedType furnishedType;
    private PriceDuration priceDuration;
    private BigDecimal securityDeposit;
    private Double squareFeetArea;
    private String description;
    private SellerType sellerType;
    private Integer ageOfProperty;
    private BigDecimal maintenanceCharges;
    private BigDecimal brokerage;
    private BhkType bhk;
    private PropertyFor propertyFor;
    private YesNo premiumProperty;
    private Status status;
    private String country;
    private String state;
    private String city;
    private Double latitude;
    private Double longitude;
    private String address;

    private String coverImage;
    private List<String> propertyGallery;
    private String videoUrl;
    private List<String> amenities;
}
