package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;

public interface CreateAiDiagnosisUseCase {
    void createAiDiagnosis(CreateAiDiagnosisRequestDto requestDto);
}
