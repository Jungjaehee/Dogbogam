package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
                .imageName(entity.getImageName())
                .imageUrl(entity.getImageUrl())
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
                .imageName(domain.getImageName())
                .imageUrl(domain.getImageUrl())
                .build();
    }

    public List<AiDiagnosis> entityListToDomainList(List<AiDiagnosisEntity> entityList) {
        if(entityList.isEmpty()) return Collections.emptyList();

        List<AiDiagnosis> aiDiagnosisList = new ArrayList<>();

        for (AiDiagnosisEntity aiDiagnosisEntity : entityList) {
            aiDiagnosisList.add(toDomain(aiDiagnosisEntity));
        }

        return aiDiagnosisList;
    }

}
