package com.dog.health.dogbogamserver.domain.medicalRecords.domain;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class MedicalRecord {
    private Long medicalRecordId;
    private Dog dog;
    private LocalDate recordDate;
    private String content;
    private String hospital;
    private String imageName;
    private String imageUrl;

    public void update(UpdateReportRequestDto updateReportRequestDto) {

    }
}
