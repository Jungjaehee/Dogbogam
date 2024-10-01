package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in;

import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;

import java.util.List;

public interface FindAiReportDiseasesUseCase {
    List<AiReportDisease> findAiReportsByDiagnosisId(Long diagnosisId);
}
