package com.example.project.dto;

import com.example.project.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1")
    private Integer duration;

    @NotNull(message = "Duration unit is required")
    private DurationUnit durationUnit;

    @NotNull(message = "Add property limit type is required")
    private LimitType addPropertyLimitType;

    @Min(value = 1, message = "Add property limit value must be at least 1")
    private Integer addPropertyLimitValue;

    @NotNull(message = "View property limit type is required")
    private LimitType viewPropertyLimitType;

    @Min(value = 1, message = "View property limit value must be at least 1")
    private Integer viewPropertyLimitValue;

    @NotNull(message = "Advertisement limit type is required")
    private LimitType advertisementLimitType;

    @Min(value = 1, message = "Advertisement limit value must be at least 1")
    private Integer advertisementLimitValue;

    @NotNull(message = "Status is required")
    private Status status;

    // These fields are read-only (set automatically in entity)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm a")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm a")
    private LocalDateTime updateDate;

}
