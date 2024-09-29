package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AiDiagnosisSpringDataRepository extends JpaRepository<AiDiagnosisEntity, Long> {
    List<AiDiagnosisEntity> findByDog(DogEntity dog);
}