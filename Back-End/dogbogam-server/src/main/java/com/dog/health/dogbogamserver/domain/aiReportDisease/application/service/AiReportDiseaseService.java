package com.dog.health.dogbogamserver.domain.aiReportDisease.application.service;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in.CreateAiReportDiseaseUseCase;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in.FindAiReportDiseaseUseCase;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in.FindAiReportDiseasesUseCase;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.CreateAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.FindAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.FindAiReportDiseasesPort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.response.FIndAiReportDiseaseResponseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiReportDiseaseService implements CreateAiReportDiseaseUseCase, FindAiReportDiseaseUseCase,
        FindAiReportDiseasesUseCase {

    private final CreateAiReportDiseasePort createAiReportDiseasePort;
    private final FindAiReportDiseasePort findAiReportDiseasePort;
    private final FindAiReportDiseasesPort findAiReportDiseasesPort;

    @Override
    public void createAiReportDisease(CreateAiReportDiseaseDto requestDto) {
        createAiReportDiseasePort.createAiReportDisease(requestDto);
    }

    @Override
    public AiReportDisease findAiReportDiseaseByAiReportDiseaseId(Long aiReportDiseaseId) {
        return findAiReportDiseasePort.findAiReportDiseaseById(aiReportDiseaseId);
    }

    @Override
    public List<FIndAiReportDiseaseResponseDto> findAiReportsByDiagnosis(AiDiagnosis aiDiagnosis) {
        return findAiReportDiseasesPort.findAiReportDiseaseByAiDiagnosis(aiDiagnosis);
    }
}
