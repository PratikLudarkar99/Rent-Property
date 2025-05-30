package com.example.project.dto;

import com.example.project.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDTO {
    private Long id;
    private Long userId;
    private Long packageId;
    private Double totalAmount;
    private String paymentType;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private LocalDate cancelledDate;
    private Status status;
}
