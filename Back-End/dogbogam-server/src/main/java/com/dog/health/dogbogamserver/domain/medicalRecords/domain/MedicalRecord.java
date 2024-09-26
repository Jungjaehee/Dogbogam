package com.dog.health.dogbogamserver.domain.medicalRecords.domain;

import com.dog.health.dogbogamserver.domain.dogs.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.dogs.domain.Dog;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class MedicalRecord {
    private Long medicalRecordId;
    private DogEntity dog;
    private LocalDateTime date;
    private String content;
    private String hospital;
    private String imageName;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
