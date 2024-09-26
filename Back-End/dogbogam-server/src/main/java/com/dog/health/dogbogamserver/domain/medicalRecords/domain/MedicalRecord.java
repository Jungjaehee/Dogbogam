package com.dog.health.dogbogamserver.domain.medicalRecords.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class MedicalRecord {
    private Long medicalRecordId;
    private Long dogId;
    private LocalDateTime date;
    private String content;
    private String hospital;
    private String imageName;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
