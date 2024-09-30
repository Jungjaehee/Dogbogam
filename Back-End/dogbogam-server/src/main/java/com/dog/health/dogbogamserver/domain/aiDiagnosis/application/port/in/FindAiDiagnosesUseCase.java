package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.CreateAiDiagnosisResponseDto;

public interface FindAiDiagnosesUseCase {
    CreateAiDiagnosisResponseDto findAiDiagnosesByDogId(Long dogId, int page, int size);
}
