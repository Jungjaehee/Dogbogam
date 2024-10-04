package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;

import java.util.List;

public interface FindAiReportDiseasesPort {
    List<AiReportDisease> findAiReportDiseaseByAiDiagnosis(AiDiagnosis aiDiagnosis);
}
