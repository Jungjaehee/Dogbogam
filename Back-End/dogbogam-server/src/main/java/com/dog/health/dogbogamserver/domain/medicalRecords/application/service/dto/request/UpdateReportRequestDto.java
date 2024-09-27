package com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request;

import java.time.LocalDate;

public class UpdateReportRequestDto {
    Long reportId;
    Long dogId;
    LocalDate recordDate;
    String content;
    String hospital;
    // File
}
