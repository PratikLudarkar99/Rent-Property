package com.example.project.service;

import org.springframework.transaction.annotation.Transactional;
import com.example.project.model.BrokerOtp;
import com.example.project.repository.BrokerOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class
BrokerServiceOtp {

    @Autowired
    private BrokerOtpRepository brokerOtpRepository;

    public String generateOtp(String phoneNumber) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        Optional<BrokerOtp> existingOtp = brokerOtpRepository.findByPhoneNumber(phoneNumber);
        if (existingOtp.isPresent()) {
            BrokerOtp existing = existingOtp.get();
            existing.setOtp(otp);
            existing.setCreatedAt(LocalDateTime.now());
            brokerOtpRepository.save(existing);
        } else {
            BrokerOtp newOtp = new BrokerOtp();
            newOtp.setPhoneNumber(phoneNumber);
            newOtp.setOtp(otp);
            newOtp.setCreatedAt(LocalDateTime.now());
            brokerOtpRepository.save(newOtp);
        }

        return otp;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        Optional<BrokerOtp> otpRecord = brokerOtpRepository.findByPhoneNumber(phoneNumber);
        return otpRecord.isPresent() && otpRecord.get().getOtp().equals(otp);
    }
    @Transactional
    public void clearOtp(String phoneNumber) {
        brokerOtpRepository.deleteByPhoneNumber(phoneNumber);
    }
}
