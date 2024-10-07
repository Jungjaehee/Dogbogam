package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisEntity;
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
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.AiReportDiseaseService;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.response.FIndAiReportDiseaseResponseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiDiagnosisReportService implements CreateAiDiagnosisUseCase, FindAiDiagnosisUseCase, FindAiDiagnosesUseCase
, DeleteAiDiagnosisUseCase {

    private final CreateAiDiagnosisPort createAiDiagnosisPort;
    private final FindAiDiagnosisPort findAiDiagnosisPort;
    private final FindAiDiagnosesPort findAiDiagnosesPort;
    private final DeleteAiDiagnosisPort deleteAiDiagnosisPort;
    private final AiReportDiseaseService aiReportDiseaseService;

    @Override
    public void createAiDiagnosis(Long memberId, CreateAiDiagnosisRequestDto requestDto) {
        createAiDiagnosisPort.createAiDiagnosis(memberId, requestDto);
    }

    @Override
    public FindAiDiagnosisResponseDto findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId) {
        AiDiagnosis aiDiagnosis = findAiDiagnosisPort.findAiDiagnosisByAiDiagnosisId(aiDiagnosisId);
        List<FIndAiReportDiseaseResponseDto> diseases = aiReportDiseaseService.findAiReportsByDiagnosis(aiDiagnosis);
        return FindAiDiagnosisResponseDto.builder()
                .aiDiagnosisId(aiDiagnosisId)
                .dogId(aiDiagnosis.getDogId())
                .normal(aiDiagnosis.getNormal())
                .imageUrl(aiDiagnosis.getImageUrl())
                .diagnosisItem(aiDiagnosis.getDiagnosisItem())
                .createdAt(aiDiagnosis.getCreatedAt().toString())
                .diseases(diseases)
                .build();
    }

    @Override
    public FindAiDiagnosesResponseDto findAiDiagnosesByDogId(Long dogId, int page, int size) {
        Page<AiDiagnosis> aiDiagnosesPage = findAiDiagnosesPort.findAiDiagnosesByDogId(dogId, page, size);

        List<AiDiagnosis> diagnoses = aiDiagnosesPage.getContent();

        List<FindAiDiagnosisResponseDto> diseases = new ArrayList<>();
        for(AiDiagnosis aiDiagnosis: diagnoses){
            diseases.add(FindAiDiagnosisResponseDto.builder()
                            .aiDiagnosisId(aiDiagnosis.getAiDiagnosisId())
                            .dogId(aiDiagnosis.getDogId())
                            .imageUrl(aiDiagnosis.getImageUrl())
                            .diagnosisItem(aiDiagnosis.getDiagnosisItem())
                            .createdAt(aiDiagnosis.getCreatedAt().toString())
                            .diseases(aiReportDiseaseService.findAiReportsByDiagnosis(aiDiagnosis))
                            .build());
        }

        return FindAiDiagnosesResponseDto.builder()
                .currantPage(aiDiagnosesPage.getNumber()+1)
                .size(aiDiagnosesPage.getSize())
                .totalElements(aiDiagnosesPage.getTotalElements())
                .totalPages(aiDiagnosesPage.getTotalPages())
                .diagnoses(diseases)
                .build();
    }

    @Override
    public void deleteAiDiagnosis(Long AiDiagnosisId) {
        deleteAiDiagnosisPort.deleteAiDiagnosis(AiDiagnosisId);
    }
}
