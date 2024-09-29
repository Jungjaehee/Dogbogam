package com.dog.health.dogbogamserver.domain.aiReportDisease.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AiReportDisease {
    private Long aiReportDiseaseId;
    private AiReportDisease aiReportDisease;
    private String name;
    private String percentage;
    private String diagnosisItem;
}
