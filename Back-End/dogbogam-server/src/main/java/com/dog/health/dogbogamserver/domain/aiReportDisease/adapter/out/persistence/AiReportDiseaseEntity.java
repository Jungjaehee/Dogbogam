package com.dog.health.dogbogamserver.domain.aiReportDisease.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence.AiDiagnosisEntity;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ai_report_diseases")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AiReportDiseaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_report_disease_id")
    private Long aiReportDiseaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_diagnosis_id", nullable = false)
    private AiDiagnosisEntity aiDiagnosis;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "percentage", columnDefinition = "DECIMAL(3, 2)",nullable = false)
    private Float percentage;

    @Column(name = "diagnosis_item", nullable = false)
    private String diagnosisItem;
}
