package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.FindAiDiagnosesResponseDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.FindAiDiagnosisResponseDto;

public interface FindAiDiagnosisUseCase {
    FindAiDiagnosisResponseDto findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId);
}
