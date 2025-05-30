package com.example.project.service;

import com.example.project.dto.SubscriptionDTO;
import com.example.project.enums.Status;
import com.example.project.model.Package;
import com.example.project.model.Subscription;
import com.example.project.model.User;
import com.example.project.repository.PackageRepository;
import com.example.project.repository.SubscriptionRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;

    public SubscriptionDTO create(SubscriptionDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Package aPackage = packageRepository.findById(dto.getPackageId()).orElseThrow();

        Subscription sub = Subscription.builder()
                .user(user)
                .aPackage(aPackage)
                .totalAmount(dto.getTotalAmount())
                .paymentType(dto.getPaymentType())
                .subscriptionStartDate(dto.getSubscriptionStartDate())
                .subscriptionEndDate(dto.getSubscriptionEndDate())
                .cancelledDate(dto.getCancelledDate())
                .status(dto.getStatus() == null ? Status.ACTIVE : dto.getStatus())
                .build();

        return toDTO(subscriptionRepository.save(sub));
    }

    public List<SubscriptionDTO> findAll() {
        return subscriptionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }

    public long countActiveSubscriptions() {
        return subscriptionRepository.countByStatus(Status.ACTIVE);
    }

    // ✅ Added update method
    public SubscriptionDTO update(Long id, SubscriptionDTO dto) {
        Subscription sub = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Package aPackage = packageRepository.findById(dto.getPackageId()).orElseThrow();

        sub.setUser(user);
        sub.setAPackage(aPackage);
        sub.setTotalAmount(dto.getTotalAmount());
        sub.setPaymentType(dto.getPaymentType());
        sub.setSubscriptionStartDate(dto.getSubscriptionStartDate());
        sub.setSubscriptionEndDate(dto.getSubscriptionEndDate());
        sub.setCancelledDate(dto.getCancelledDate());
        sub.setStatus(dto.getStatus());

        return toDTO(subscriptionRepository.save(sub));
    }

    private SubscriptionDTO toDTO(Subscription sub) {
        return SubscriptionDTO.builder()
                .id(sub.getId())
                .userId(sub.getUser().getId())
                .packageId(sub.getAPackage().getId())
                .totalAmount(sub.getTotalAmount())
                .paymentType(sub.getPaymentType())
                .subscriptionStartDate(sub.getSubscriptionStartDate())
                .subscriptionEndDate(sub.getSubscriptionEndDate())
                .cancelledDate(sub.getCancelledDate())
                .status(sub.getStatus())
                .build();
    }
}
