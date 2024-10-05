package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.dog.application.service.DogService;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AiDiagnosisMapper {
    private final DogMapper dogMapper;
    private final DogService dogService;

    public AiDiagnosis toDomain(AiDiagnosisEntity entity) {
        if(entity == null) return null;
        return AiDiagnosis.builder()
                .dogId(entity.getDogId())
                .aiDiagnosisId(entity.getAiDiagnosisId())
                .diagnosisItem(entity.getDiagnosisItem())
                .normal(entity.getNormal())
                .imageName(entity.getImageName())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public AiDiagnosisEntity toEntity(AiDiagnosis domain) {
        if(domain == null) return null;
        return AiDiagnosisEntity.builder()
                .aiDiagnosisId(domain.getAiDiagnosisId())
                .dogId(domain.getDogId())
                .diagnosisItem(domain.getDiagnosisItem())
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

    public Page<AiDiagnosis> entityPagetoDomainPage(Page<AiDiagnosisEntity> entityPage) {
        return entityPage.map(this::toDomain); // map() 메서드를 사용하여 각 엔티티를 도메인 객체로 변환
    }
}
