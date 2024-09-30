package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in;

import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;

public interface FindAiReportDiseaseUseCase {
    AiReportDisease findAiReportDiseaseByAiReportDiseaseId(Long aiReportDiseaseId);
}
