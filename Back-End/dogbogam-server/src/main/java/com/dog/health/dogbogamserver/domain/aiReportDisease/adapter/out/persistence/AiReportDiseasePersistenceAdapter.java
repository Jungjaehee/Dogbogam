package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisMapper;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisReportPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.CreateAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.FindAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.FindAiReportDiseasesPort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.response.FIndAiReportDiseaseResponseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiReportDiseasePersistenceAdapter implements CreateAiReportDiseasePort, FindAiReportDiseasePort,
        FindAiReportDiseasesPort {

    private final AiReportDiseaseSpringDataRepository jpaRepository;
    private final AiDiagnosisReportPersistenceAdapter diagnosisAdapter;
    private final AiDiagnosisMapper aiDiagnosisMapper;
    private final AiReportDiseaseMapper aiReportDiseaseMapper;

    @Override
    @Transactional
    public void createAiReportDisease(CreateAiReportDiseaseDto requestDto) {
        AiDiagnosis aiDiagnosis = diagnosisAdapter.findAiDiagnosisByAiDiagnosisId(
                requestDto.getAiDiagnosisId());
        AiReportDiseaseEntity aiReportDiseaseEntity = AiReportDiseaseEntity.builder()
                .aiDiagnosis(aiDiagnosisMapper.toEntity(aiDiagnosis))
                .disease(requestDto.getDisease())
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

    @Override
    public List<FIndAiReportDiseaseResponseDto> findAiReportDiseaseByAiDiagnosis(AiDiagnosis aiDiagnosis) {
        if(aiDiagnosis == null) {
            throw new CustomException(ErrorCode.AI_DIAGNOSIS_NOT_FOUND);
        }

        List<AiReportDiseaseEntity> entityList = jpaRepository.findAiReportDiseaseEntitiesByAiDiagnosis(
                aiDiagnosisMapper.toEntity(aiDiagnosis));

        List<AiReportDisease> domainList = aiReportDiseaseMapper.entityListToDomainList(entityList);

        List<FIndAiReportDiseaseResponseDto> responseDtoList = new ArrayList<>();

        for(AiReportDisease aiReportDisease : domainList) {
            responseDtoList.add(FIndAiReportDiseaseResponseDto.builder()
                            .disease(aiReportDisease.getDisease())
                            .percentage(aiReportDisease.getPercentage())
                            .build());
        }

        return responseDtoList;
    }
}
