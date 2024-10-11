package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisMapper;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiReportDiseaseMapper {

    private final AiDiagnosisMapper aiDiagnosisMapper;

    public AiReportDisease toDomain(AiReportDiseaseEntity entity) {
        if (entity == null) return null;
        return AiReportDisease.builder()
                .aiReportDiseaseId(entity.getAiReportDiseaseId())
                .aiDiagnosis(aiDiagnosisMapper.toDomain(entity.getAiDiagnosis()))
                .disease(entity.getDisease())
                .percentage(entity.getPercentage())
                .build();
    }

    public AiReportDiseaseEntity toEntity(AiReportDisease domain) {
        if (domain == null) return null;
        return AiReportDiseaseEntity.builder()
                .aiReportDiseaseId(domain.getAiReportDiseaseId())
                .aiDiagnosis(aiDiagnosisMapper.toEntity(domain.getAiDiagnosis()))
                .disease(domain.getDisease())
                .percentage(domain.getPercentage())
                .build();
    }

    public List<AiReportDisease> entityListToDomainList(List<AiReportDiseaseEntity> entityList) {
        if(entityList.isEmpty()) return Collections.emptyList();

        List<AiReportDisease> aiReportDiseaseList = new ArrayList<>();

        for (AiReportDiseaseEntity aiDiagnosisEntity : entityList) {
            aiReportDiseaseList.add(toDomain(aiDiagnosisEntity));
        }

        return aiReportDiseaseList;
    }
}
