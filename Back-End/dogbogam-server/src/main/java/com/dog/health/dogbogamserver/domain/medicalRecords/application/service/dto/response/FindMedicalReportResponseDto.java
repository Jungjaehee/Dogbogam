package com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindMedicalReportResponseDto {
    Long dogId;
    LocalDateTime recordTime;
    String content;
    String hospital;
    Long cost;
    String imageUrl;
}
