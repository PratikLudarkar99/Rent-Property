package com.example.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardResponse {
    private long totalCustomers;
    private long totalBrokers;
    private long totalProperties;
    private long activeSubscriptions;
    private List<RecentInquiryDTO> recentInquiries;
}
