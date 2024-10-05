package com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.response.FIndAiReportDiseaseResponseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.domain.AiReportDisease;

import java.util.List;

public interface FindAiReportDiseasesUseCase {
    List<FIndAiReportDiseaseResponseDto> findAiReportsByDiagnosis(AiDiagnosis aiDiagnosis);
}
