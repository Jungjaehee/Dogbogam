package com.dog.health.dogbogamserver.domain.medicalRecords.domain;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class MedicalRecord {
    private Long medicalRecordId;
    private Dog dog;
    private LocalDateTime recordTime;
    private String content;
    private String hospital;
    private Long cost;
    private String imageName;
    private String imageUrl;
}
