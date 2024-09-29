package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiDiagnosisMapper {
    private final DogMapper dogMapper;

    public AiDiagnosis toDomain(AiDiagnosisEntity entity) {
        if(entity == null) return null;
        return AiDiagnosis.builder()
                .dog(dogMapper.toDomain(entity.getDog()))
                .aiDiagnosisId(entity.getAiDiagnosisId())
                .diagnosisItem(entity.getDiagnosisItem())
                .dog(dogMapper.toDomain(entity.getDog()))
                .normal(entity.getNormal())
                // 이미지
                .build();
    }

    public AiDiagnosisEntity toEntity(AiDiagnosis domain) {
        if(domain == null) return null;
        return AiDiagnosisEntity.builder()
                .dog(dogMapper.toEntity(domain.getDog()))
                .aiDiagnosisId(domain.getAiDiagnosisId())
                .diagnosisItem(domain.getDiagnosisItem())
                .dog(dogMapper.toEntity(domain.getDog()))
                .normal(domain.getNormal())
                // 이미지
                .build();
    }
}
