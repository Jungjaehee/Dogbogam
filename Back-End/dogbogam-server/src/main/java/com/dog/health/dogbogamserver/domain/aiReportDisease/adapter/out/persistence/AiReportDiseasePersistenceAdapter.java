package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisEntity;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisMapper;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.CreateAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiReportDiseasePersistenceAdapter implements CreateAiReportDiseasePort {

    private final AiReportDiseaseMapper DiseaseMapper;
    private final AiReportDiseaseSpringDataRepository jpaRepository;
    private final AiDiagnosisPersistenceAdapter persistenceAdapter;
    private final AiDiagnosisMapper DiagnosisMapper;

    @Override
    @Transactional
    public void createAiReportDisease(CreateAiReportDiseaseDto requestDto) {
        AiDiagnosis aiDiagnosis = persistenceAdapter.findAiDiagnosisByAiDiagnosisId(
                requestDto.getAiDiagnosisId());
        AiReportDiseaseEntity aiReportDiseaseEntity = AiReportDiseaseEntity.builder()
                .diagnosisItem(requestDto.getDiagnosisItem())
                .aiDiagnosis(DiagnosisMapper.toEntity(aiDiagnosis))
                .name(requestDto.getName())
                .percentage(requestDto.getPercentage())
                .build();
        jpaRepository.save(aiReportDiseaseEntity);
    }
}
