package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AiReportDiseaseSpringDataRepository extends JpaRepository<AiReportDiseaseEntity, Long> {
    List<AiReportDiseaseEntity> findAiReportDiseaseEntitiesByAiDiagnosis(AiDiagnosisEntity aiDiagnosisEntity);
}
