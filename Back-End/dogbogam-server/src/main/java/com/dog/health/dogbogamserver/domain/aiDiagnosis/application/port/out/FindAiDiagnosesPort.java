package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

import java.util.List;

public interface FindAiDiagnosesPort {
    List<AiDiagnosis> findAiDiagnosesByDogId(Long dogId);
}
