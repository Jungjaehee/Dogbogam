package com.dog.health.dogbogamserver.domain.aiReportDisease.application.service;

import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in.CreateAiReportDiseaseUseCase;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.CreateAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiReportDiseaseService implements CreateAiReportDiseaseUseCase {

    private final CreateAiReportDiseasePort createAiReportDiseasePort;

    @Override
    public void createAiReportDisease(CreateAiReportDiseaseDto requestDto) {
        createAiReportDiseasePort.createAiReportDisease(requestDto);
    }
}
