package com.example.project.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecentInquiryDTO {
    private String customerName;
    private String propertyTitle;
    private String brokerName;
    private LocalDateTime inquiryDate;
}
