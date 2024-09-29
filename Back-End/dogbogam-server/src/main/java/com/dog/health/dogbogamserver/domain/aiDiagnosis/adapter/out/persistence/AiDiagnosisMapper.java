package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiDiagnosisMapper {
    private DogMapper dogMapper;

    public AiDiagnosis toDomain(AiDiagnosisEntity entity) {
        if(entity == null) return null;
        return AiDiagnosis.builder()
                .aiDiagnosisId(entity.getAiDiagnosisId())
                .diagnosisItem(entity.getDiagnosisItem())
                .dog(dogMapper.toDomain(entity.getDog()))
                .normal(entity.getNormal())
                // 이미지
                .build();
    }

    public AiDiagnosisEntity toEntity(AiDiagnosis diagnosis) {
        if(diagnosis == null) return null;
        return AiDiagnosisEntity.builder()
                .aiDiagnosisId(diagnosis.getAiDiagnosisId())
                .diagnosisItem(diagnosis.getDiagnosisItem())
                .dog(dogMapper.toEntity(diagnosis.getDog()))
                .normal(diagnosis.getNormal())
                // 이미지
                .build();
    }
}
