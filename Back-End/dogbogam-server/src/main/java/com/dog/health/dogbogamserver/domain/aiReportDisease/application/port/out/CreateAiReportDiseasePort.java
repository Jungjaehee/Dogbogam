package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out;

import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;

public interface CreateAiReportDiseasePort {
    void createAiReportDisease(CreateAiReportDiseaseDto requestDto);
}
