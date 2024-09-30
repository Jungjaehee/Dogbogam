package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.CreateAiDiagnosisResponseDto;

public interface FindAiDiagnosesPort {
    CreateAiDiagnosisResponseDto findAiDiagnosesByDogId(Long dogId, int page, int size);
}
