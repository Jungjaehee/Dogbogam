package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AiDiagnosisSpringDataRepository extends JpaRepository<AiDiagnosisEntity, Long> {
    Page<AiDiagnosisEntity> findByDog_DogIdAndDiagnosisItemNot(Long dogId, String diagnosisItem,Pageable pageable);
}