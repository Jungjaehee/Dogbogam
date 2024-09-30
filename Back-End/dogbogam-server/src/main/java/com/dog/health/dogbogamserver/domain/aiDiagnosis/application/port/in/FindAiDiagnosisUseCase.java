package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

public interface FindAiDiagnosisUseCase {
    AiDiagnosis findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId);
}
