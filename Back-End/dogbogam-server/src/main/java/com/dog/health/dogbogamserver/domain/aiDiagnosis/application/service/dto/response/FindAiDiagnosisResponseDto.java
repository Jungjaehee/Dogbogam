package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response;

import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class FindAiDiagnosisResponseDto {
    Long aiDiagnosisId;
    Long dogId;
    String imageUrl;
    Boolean normal;
    String diagnosisItem;
    List<AiReportDisease> diseases;
}
