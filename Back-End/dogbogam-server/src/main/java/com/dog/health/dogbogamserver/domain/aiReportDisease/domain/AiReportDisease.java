package com.dog.health.dogbogamserver.domain.aiReportDisease.domain;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AiReportDisease {
    private Long aiReportDiseaseId;
    private AiDiagnosis aiDiagnosis;
    private String name;
    private Float percentage;
    private String diagnosisItem;
}
