package com.example.project.controller;

import com.example.project.dto.AmenityDTO;
import com.example.project.service.AmenityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
@CrossOrigin("*")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    // ✅ Updated method: Add Amenity with file upload
    @PostMapping("/add")
    public ResponseEntity<?> createAmenity(
            @RequestParam("name") String name,
            @RequestParam("value") String value,
            @RequestParam("status") String status,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            // 🛑 Validation checks
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (value == null || value.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Value is required");
            }
            if (status == null || status.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Status is required");
            }
            if (imageFile == null || imageFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Image file is required");
            }

            // ✅ Populate DTO
            AmenityDTO dto = new AmenityDTO();
            dto.setName(name.trim());
            dto.setValue(value.trim());
            dto.setStatus(status.trim());
            dto.setImage(imageFile.getBytes());

            // ✅ Call service
            AmenityDTO saved = amenityService.saveAmenity(dto);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to process amenity data: " + e.getMessage());
        }
    }



    @GetMapping("/get")
    public ResponseEntity<List<AmenityDTO>> getAllAmenities() {
        List<AmenityDTO> amenities = amenityService.getAllAmenities();
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AmenityDTO> getAmenityById(@PathVariable Long id) {
        return amenityService.getAmenityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AmenityDTO> updateAmenity(@PathVariable Long id,
                                                    @Valid @RequestBody AmenityDTO dto) {
        return amenityService.getAmenityById(id).map(existing -> {
            dto.setId(id);
            AmenityDTO updated = amenityService.saveAmenity(dto);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }
}
