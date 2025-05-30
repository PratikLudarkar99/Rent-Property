package com.example.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmenityDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image; // Storing image as a byte array
    private String value;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy h:mm a")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy h:mm a")
    private LocalDateTime updatedDate;


    // createdDate and updatedDate are managed by backend
}
