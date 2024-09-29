package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

import java.util.List;
import java.util.Optional;

public interface FindAiDiagnosesUseCase {
    Optional<List<AiDiagnosis>> findAiDiagnosesByDogId(Long dogId);
}
