package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AiDiagnosisSpringDataRepository extends JpaRepository<AiDiagnosisEntity, Long> {
    Page<AiDiagnosisEntity> findByDog(DogEntity dog, Pageable pageable);
}