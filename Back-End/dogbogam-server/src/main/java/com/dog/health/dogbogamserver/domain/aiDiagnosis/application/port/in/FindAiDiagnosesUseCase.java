package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

import java.util.List;

public interface FindAiDiagnosesUseCase {
    List<AiDiagnosis> findAiDiagnosesByDogId(Long dogId);
}
