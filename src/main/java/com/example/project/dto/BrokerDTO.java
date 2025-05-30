package com.example.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrokerDTO {
    private Long brokerId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String agencyName;
    private Integer yearsOfExperience;
    private String aboutYou;
    private byte[] profileImage;
}
