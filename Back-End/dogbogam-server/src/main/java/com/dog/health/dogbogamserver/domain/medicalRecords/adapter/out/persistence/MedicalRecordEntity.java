package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class MedicalRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dogId;
    private LocalDateTime date;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String hospital;
    private String imageName;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
