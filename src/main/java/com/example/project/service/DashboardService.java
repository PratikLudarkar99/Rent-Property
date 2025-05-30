package com.example.project.service;

import com.example.project.dto.DashboardResponse;
import com.example.project.dto.RecentInquiryDTO;
import com.example.project.model.Dashboard;
import com.example.project.repository.*;
import com.example.project.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private InquiryRepository enquiryRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    public DashboardResponse getDashboardData() {
        DashboardResponse response = new DashboardResponse();

        long totalCustomers = customerRepository.count();
        long totalBrokers = brokerRepository.count();
        long totalProperties = propertyRepository.count();
        long activeSubscriptions = subscriptionRepository.countByStatus(Status.ACTIVE);

        // Set dashboard metrics
        response.setTotalCustomers(totalCustomers);
        response.setTotalBrokers(totalBrokers);
        response.setTotalProperties(totalProperties);
        response.setActiveSubscriptions(activeSubscriptions);

        // Save to dashboard table
        Dashboard dashboard = new Dashboard();
        dashboard.setTotalCustomers(totalCustomers);
        dashboard.setTotalBrokers(totalBrokers);
        dashboard.setTotalProperties(totalProperties);
        dashboard.setActiveSubscriptions(activeSubscriptions);
        dashboard.setLastUpdated(LocalDateTime.now());

        dashboardRepository.save(dashboard);

        // Fetch top 5 recent inquiries sorted by createdDate (assuming 'createdDate' exists in Enquiry entity)
        response.setRecentInquiries(
                enquiryRepository


                        .findTop5ByOrderByCreatedDateDesc()
                        .stream()
                        .map(inquiry -> {
                            RecentInquiryDTO dto = new RecentInquiryDTO();
                            dto.setCustomerName(
                                    inquiry.getCustomer().getFirstName() + " " + inquiry.getCustomer().getLastName()
                            );
                            dto.setPropertyTitle(inquiry.getProperty()
                                    .getTitle());

                            dto.setInquiryDate(inquiry.getCreatedDate()); // Make sure this matches your entity field
                            return dto;
                        })
                        .collect(Collectors.toList())
        );

        return response;
    }
}
