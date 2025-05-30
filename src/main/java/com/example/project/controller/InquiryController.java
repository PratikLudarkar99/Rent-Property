package com.example.project.controller;

import com.example.project.dto.InquiryDTO;
import com.example.project.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
@CrossOrigin
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // GET all inquiries (Admin)
    @GetMapping("/get")
    public ResponseEntity<List<InquiryDTO>> getAllInquiriesForAdmin() {
        return ResponseEntity.ok(inquiryService.getAllInquiriesForAdmin());
    }

    // POST create new inquiry
    @PostMapping("/add")
    public ResponseEntity<InquiryDTO> createInquiry(@RequestBody InquiryDTO inquiryDTO) {
        InquiryDTO created = inquiryService.createInquiry(inquiryDTO);
        return ResponseEntity.ok(created);
    }

    // PUT update existing inquiry
    @PutMapping("/updated/{id}") // previously: "/update/{id}"
    public ResponseEntity<InquiryDTO> updateInquiry(@PathVariable Long id, @RequestBody InquiryDTO inquiryDTO) {
        InquiryDTO updated = inquiryService.updateInquiry(id, inquiryDTO);
        return ResponseEntity.ok(updated);
    }


    // DELETE inquiry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.noContent().build();
    }
}
