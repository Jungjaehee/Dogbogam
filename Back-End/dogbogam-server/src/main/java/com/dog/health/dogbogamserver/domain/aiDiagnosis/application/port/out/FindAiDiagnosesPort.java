package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.FindAiDiagnosesResponseDto;

public interface FindAiDiagnosesPort {
    FindAiDiagnosesResponseDto findAiDiagnosesByDogId(Long dogId, int page, int size);
}
