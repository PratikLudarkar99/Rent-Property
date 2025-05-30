package com.example.project.service;

import com.example.project.dto.AmenityDTO;
import com.example.project.model.Amenity;
import com.example.project.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    private AmenityDTO mapToDTO(Amenity amenity) {
        return AmenityDTO.builder()
                .id(amenity.getId())
                .name(amenity.getName())
                .image(amenity.getImage()) // byte[] stays byte[] here
                .value(amenity.getValue())
                .status(amenity.getStatus())
                .createdDate(amenity.getCreatedDate())
                .updatedDate(amenity.getUpdatedDate())

                .build();
    }

    private Amenity mapToEntity(AmenityDTO dto) {
        return Amenity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .image(dto.getImage()) // byte[] stays byte[] here
                .value(dto.getValue())
                .status(dto.getStatus())
                .build();
    }

    public AmenityDTO saveAmenity(AmenityDTO dto) {
        Amenity amenity = mapToEntity(dto);
        Amenity saved = amenityRepository.save(amenity);
        return mapToDTO(saved);
    }

    public List<AmenityDTO> getAllAmenities() {
        List<Amenity> amenities = amenityRepository.findAll();
        return amenities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<AmenityDTO> getAmenityById(Long id) {
        return amenityRepository.findById(id).map(this::mapToDTO);
    }

    public void deleteAmenity(Long id) {
        amenityRepository.deleteById(id);
    }
}
