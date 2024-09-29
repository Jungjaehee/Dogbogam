package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;

public interface CreateAiDiagnosisPort {
    void createAiDiagnosis(CreateAiDiagnosisRequestDto requestDto);
}
