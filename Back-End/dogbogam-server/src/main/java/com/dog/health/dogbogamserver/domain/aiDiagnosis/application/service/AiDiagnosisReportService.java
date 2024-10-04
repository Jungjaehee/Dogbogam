package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.CreateAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.DeleteAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosesUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.CreateAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.DeleteAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosesPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.FindAiDiagnosesResponseDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.FindAiDiagnosisResponseDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.FindAiReportDiseasesPort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiDiagnosisReportService implements CreateAiDiagnosisUseCase, FindAiDiagnosisUseCase, FindAiDiagnosesUseCase
, DeleteAiDiagnosisUseCase {

    private final CreateAiDiagnosisPort createAiDiagnosisPort;
    private final FindAiDiagnosisPort findAiDiagnosisPort;
    private final FindAiDiagnosesPort findAiDiagnosesPort;
    private final DeleteAiDiagnosisPort deleteAiDiagnosisPort;
    private final FindAiReportDiseasesPort findAiReportDiseasesPort;

    @Override
    public void createAiDiagnosis(Long memberId, CreateAiDiagnosisRequestDto requestDto) {
        createAiDiagnosisPort.createAiDiagnosis(memberId, requestDto);
    }

    @Override
    public FindAiDiagnosisResponseDto findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId) {
        AiDiagnosis aiDiagnosis = findAiDiagnosisPort.findAiDiagnosisByAiDiagnosisId(aiDiagnosisId);
        List<AiReportDisease> diseases = findAiReportDiseasesPort.findAiReportDiseaseByAiDiagnosis(aiDiagnosis);
        return FindAiDiagnosisResponseDto.builder()
                .aiDiagnosisId(aiDiagnosisId)
                .dogId(aiDiagnosis.getDog().getDogId())
                .normal(aiDiagnosis.getNormal())
                .imageUrl(aiDiagnosis.getImageUrl())
                .diagnosisItem(aiDiagnosis.getDiagnosisItem())
                .diseases(diseases)
                .build();
    }

    @Override
    public FindAiDiagnosesResponseDto findAiDiagnosesByDogId(Long dogId, int page, int size) {
        return findAiDiagnosesPort.findAiDiagnosesByDogId(dogId, page, size);
    }

    @Override
    public void deleteAiDiagnosis(Long AiDiagnosisId) {
        deleteAiDiagnosisPort.deleteAiDiagnosis(AiDiagnosisId);
    }
}
