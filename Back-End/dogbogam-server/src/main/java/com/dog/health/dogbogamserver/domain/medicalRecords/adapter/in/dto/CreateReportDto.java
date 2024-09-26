package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateReportDto {
    Long dogId;
    LocalDate recordDate;
    String content;
    String hospital;
    // File
}
