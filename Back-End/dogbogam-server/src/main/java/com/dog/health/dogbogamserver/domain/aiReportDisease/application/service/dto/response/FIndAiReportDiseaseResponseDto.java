package com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FIndAiReportDiseaseResponseDto {
    String disease;
    Float percentage;
}
