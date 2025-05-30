package com.example.project.repository;

import com.example.project.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    // Get latest 5 inquiries
    List<Inquiry> findTop5ByOrderByCreatedDateDesc();

    // ✅ Delete all inquiries by customer ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Inquiry i WHERE i.customer.customerId = :customerId")
    void deleteByCustomerId(@Param("customerId") Long customerId);
}
