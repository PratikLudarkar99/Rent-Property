package com.example.project.service;

import com.example.project.dto.PackageDTO;
import com.example.project.enums.LimitType;
import com.example.project.mapper.PackageMapper;
import com.example.project.model.Package;
import com.example.project.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    // ✅ Refactored to use PackageMapper
    public PackageDTO create(PackageDTO dto) {
        Package entity = PackageMapper.toEntity(dto);
        Package savedEntity = packageRepository.save(entity);
        return PackageMapper.toDto(savedEntity);
    }

    public List<PackageDTO> findAll() {
        return packageRepository.findAll()
                .stream()
                .map(PackageMapper::toDto)
                .collect(Collectors.toList());
    }

    public PackageDTO update(PackageDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Package ID must not be null for update");
        }

        Optional<Package> optionalPackage = packageRepository.findById(dto.getId());
        if (optionalPackage.isEmpty()) {
            throw new RuntimeException("Package not found with id " + dto.getId());
        }

        Package existingPackage = optionalPackage.get();

        // Update fields
        existingPackage.setName(dto.getName());
        existingPackage.setDescription(dto.getDescription());
        existingPackage.setPrice(dto.getPrice());
        existingPackage.setDuration(dto.getDuration());
        existingPackage.setDurationUnit(dto.getDurationUnit());
        existingPackage.setAddPropertyLimitType(dto.getAddPropertyLimitType());
        existingPackage.setAddPropertyLimitValue(
                dto.getAddPropertyLimitType() == LimitType.LIMIT ? dto.getAddPropertyLimitValue() : null);
        existingPackage.setViewPropertyLimitType(dto.getViewPropertyLimitType());
        existingPackage.setViewPropertyLimitValue(
                dto.getViewPropertyLimitType() == LimitType.LIMIT ? dto.getViewPropertyLimitValue() : null);
        existingPackage.setAdvertisementLimitType(dto.getAdvertisementLimitType());
        existingPackage.setAdvertisementLimitValue(
                dto.getAdvertisementLimitType() == LimitType.LIMIT ? dto.getAdvertisementLimitValue() : null);
        existingPackage.setStatus(dto.getStatus());

        Package updatedPackage = packageRepository.save(existingPackage);
        return PackageMapper.toDto(updatedPackage);
    }

    public void delete(Long id) {
        if (!packageRepository.existsById(id)) {
            throw new RuntimeException("Package not found with id " + id);
        }
        packageRepository.deleteById(id);
    }
}
