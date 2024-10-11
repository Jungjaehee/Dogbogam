package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import org.springframework.data.domain.Page;

public interface FindAiDiagnosesPort {
    Page<AiDiagnosis> findAiDiagnosesByDogId(Long dogId, int page, int size);
}
