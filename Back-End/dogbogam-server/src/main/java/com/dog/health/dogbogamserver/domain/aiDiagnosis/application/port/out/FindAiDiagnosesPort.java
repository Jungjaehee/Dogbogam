package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

import java.util.List;
import java.util.Optional;

public interface FindAiDiagnosesPort {
    Optional<List<AiDiagnosis>> findAiDiagnosesByDogId(Long dogId);
}
