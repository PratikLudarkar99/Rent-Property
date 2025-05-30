package com.example.project.mapper;

import com.example.project.dto.PackageDTO;
import com.example.project.model.Package;

public class PackageMapper {

    public static PackageDTO toDto(Package pkg) {
        return PackageDTO.builder()
                .id(pkg.getId())
                .name(pkg.getName())
                .description(pkg.getDescription())
                .price(pkg.getPrice())
                .duration(pkg.getDuration())
                .durationUnit(pkg.getDurationUnit())
                .addPropertyLimitType(pkg.getAddPropertyLimitType())
                .addPropertyLimitValue(pkg.getAddPropertyLimitValue())
                .viewPropertyLimitType(pkg.getViewPropertyLimitType())
                .viewPropertyLimitValue(pkg.getViewPropertyLimitValue())
                .advertisementLimitType(pkg.getAdvertisementLimitType())
                .advertisementLimitValue(pkg.getAdvertisementLimitValue())
                .status(pkg.getStatus())
                .createDate(pkg.getCreateDate())       // Important for created date
                .updateDate(pkg.getUpdateDate())       // Important for updated date
                .build();
    }

    public static Package toEntity(PackageDTO dto) {
        return Package.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .durationUnit(dto.getDurationUnit())
                .addPropertyLimitType(dto.getAddPropertyLimitType())
                .addPropertyLimitValue(dto.getAddPropertyLimitValue())
                .viewPropertyLimitType(dto.getViewPropertyLimitType())
                .viewPropertyLimitValue(dto.getViewPropertyLimitValue())
                .advertisementLimitType(dto.getAdvertisementLimitType())
                .advertisementLimitValue(dto.getAdvertisementLimitValue())
                .status(dto.getStatus())
                // createDate and updateDate should NOT be set here
                .build();
    }
}
