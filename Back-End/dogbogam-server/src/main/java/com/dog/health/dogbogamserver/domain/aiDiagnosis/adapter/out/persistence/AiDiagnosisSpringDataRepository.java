package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AiDiagnosisSpringDataRepository extends JpaRepository<AiDiagnosisEntity, Long> {
}
