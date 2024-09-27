package com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateReportRequestDto {
    Long reportId;
    Long dogId;
    LocalDate recordDate;
    String content;
    String hospital;
    // File
}
