package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisEntity;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisMapper;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.CreateAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.FindAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiReportDiseasePersistenceAdapter implements CreateAiReportDiseasePort, FindAiReportDiseasePort {

    private final AiReportDiseaseMapper DiseaseMapper;
    private final AiReportDiseaseSpringDataRepository jpaRepository;
    private final AiDiagnosisPersistenceAdapter persistenceAdapter;
    private final AiDiagnosisMapper DiagnosisMapper;
    private final AiDiagnosisMapper aiDiagnosisMapper;
    private final AiReportDiseaseMapper aiReportDiseaseMapper;

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

    @Override
    public AiReportDisease findAiReportDiseaseById(Long id) {
        AiReportDiseaseEntity entity = jpaRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.AI_DIAGNOSIS_NOT_FOUND));

        return aiReportDiseaseMapper.toDomain(entity);
    }
}
