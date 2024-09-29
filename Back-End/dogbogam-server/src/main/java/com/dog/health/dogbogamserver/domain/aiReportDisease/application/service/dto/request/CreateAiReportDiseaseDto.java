package com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request;

import lombok.Getter;

@Getter
public class CreateAiReportDiseaseDto {
    private Long aiDiagnosisId;
    private String name;
    private Float percentage;
    private String diagnosisItem;
}
