package com.example.project.repository;
import com.example.project.model.BrokerOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BrokerOtpRepository extends JpaRepository<BrokerOtp, Long>{

    Optional<BrokerOtp> findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
}



