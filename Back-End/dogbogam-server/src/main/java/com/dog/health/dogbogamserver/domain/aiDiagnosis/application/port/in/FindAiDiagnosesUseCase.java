package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.FindAiDiagnosesResponseDto;

import java.util.List;

public interface FindAiDiagnosesUseCase {
    FindAiDiagnosesResponseDto findAiDiagnosesByDogId(Long dogId, int page, int size);
}
