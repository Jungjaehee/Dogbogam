package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out;

import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;

public interface FindAiReportDiseasePort {
    AiReportDisease findAiReportDiseaseById(Long id);
}
