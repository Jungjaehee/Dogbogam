package com.dog.health.dogbogamserver.domain.healthProblem.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.HealthProblem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HealthProblemMapper {

    private final DogMapper dogMapper;

    public HealthProblemEntity toEntity(HealthProblem healthProblem) {
        return HealthProblemEntity.builder()
                .dog(dogMapper.toEntity(healthProblem.getDog()))  // Dog 변환
                .problem(healthProblem.getProblem())
                .build();
    }

    public HealthProblem toDomain(HealthProblemEntity entity) {
        return HealthProblem.builder()
                .healthProblemId(entity.getHealthProblemId())
                .dog(dogMapper.toDomain(entity.getDog()))  // Dog 변환
                .problem(entity.getProblem())
                .build();
    }
}
