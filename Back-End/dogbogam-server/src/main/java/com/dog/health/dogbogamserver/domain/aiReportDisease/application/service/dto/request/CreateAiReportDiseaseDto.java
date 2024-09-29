package com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

public class CreateAiReportDiseaseDto {
    private Long aiDiagnosisId;
    private String name;
    private Float percentage;
    private String diagnosisItem;
}
