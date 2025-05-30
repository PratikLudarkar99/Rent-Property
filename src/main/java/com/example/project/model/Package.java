package com.example.project.model;

import com.example.project.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "package")
public class Package {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Column(nullable = false)
        private String name;

        @NotNull
        @Column(nullable = false)
        private String description;

        @NotNull
        @Column(nullable = false)
        private Double price;

        @NotNull
        @Column(nullable = false)
        private Integer duration;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private DurationUnit durationUnit;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private LimitType addPropertyLimitType;

        @Column(nullable = true) // Nullable based on LIMIT type
        private Integer addPropertyLimitValue;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private LimitType viewPropertyLimitType;

        @Column(nullable = true) // Nullable based on LIMIT type
        private Integer viewPropertyLimitValue;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private LimitType advertisementLimitType;

        @Column(nullable = true) // Nullable based on LIMIT type
        private Integer advertisementLimitValue;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 20)
        private Status status;

        @NotNull
        @Column(nullable = false)
        private LocalDateTime createDate;

        @NotNull
        @Column(nullable = false)
        private LocalDateTime updateDate;

        @PrePersist
        public void onCreate() {
                this.createDate = LocalDateTime.now();
                this.updateDate = LocalDateTime.now();
        }

        @PreUpdate
        public void onUpdate() {
                this.updateDate = LocalDateTime.now();
        }
}
