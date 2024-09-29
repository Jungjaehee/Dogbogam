package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisMapper;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiReportDiseaseMapper {

    private AiDiagnosisMapper aiDiagnosisMapper;

    public AiReportDisease toDomain(AiReportDiseaseEntity entity) {
        if (entity == null) return null;
        return AiReportDisease.builder()
                .aiReportDiseaseId(entity.getAiReportDiseaseId())
                .aiDiagnosis(aiDiagnosisMapper.toDomain(entity.getAiDiagnosis()))
                .name(entity.getName())
                .percentage(entity.getPercentage())
                .diagnosisItem(entity.getDiagnosisItem())
                .build();
    }

    public AiReportDiseaseEntity toEntity(AiReportDisease domain) {
        if (domain == null) return null;
        return AiReportDiseaseEntity.builder()
                .aiReportDiseaseId(domain.getAiReportDiseaseId())
                .aiDiagnosis(aiDiagnosisMapper.toEntity(domain.getAiDiagnosis()))
                .name(domain.getName())
                .percentage(domain.getPercentage())
                .diagnosisItem(domain.getDiagnosisItem())
                .build();
    }
}
