package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in;

import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;

public interface CreateAiReportDiseaseUseCase {
    void createAiReportDisease(CreateAiReportDiseaseDto requestDto);
}
