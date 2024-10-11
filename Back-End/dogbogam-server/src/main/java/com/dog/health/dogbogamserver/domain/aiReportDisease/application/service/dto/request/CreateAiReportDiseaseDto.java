package com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAiReportDiseaseDto {
    private Long aiDiagnosisId;
    private String disease;
    private Float percentage;
}
