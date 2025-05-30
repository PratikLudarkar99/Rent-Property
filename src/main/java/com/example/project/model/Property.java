package com.example.project.model;

import com.example.project.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyCategory category;

    @Column(nullable = false)
    private String propertyName;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FurnishedType furnishedType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceDuration priceDuration;

    @Column(nullable = false)
    private BigDecimal securityDeposit;

    @Column(nullable = false)
    private Double squareFeetArea;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SellerType sellerType;

    @Column(nullable = false)
    private Integer ageOfProperty; // in years

    @Column(nullable = false)
    private BigDecimal maintenanceCharges; // per month

    @Column(nullable = false)
    private BigDecimal brokerage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BhkType bhk;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyFor propertyFor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YesNo premiumProperty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String address;
    private String coverImage;

    @Column(columnDefinition = "TEXT")
    private String propertyGallery;

    private String videoUrl; // YouTube or hosted link

    @Column(columnDefinition = "TEXT")
    private String amenities;


    @Column
    private String title;
}
