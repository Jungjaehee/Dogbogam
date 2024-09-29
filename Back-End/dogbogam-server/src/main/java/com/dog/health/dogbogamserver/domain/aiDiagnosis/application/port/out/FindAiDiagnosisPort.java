package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;

public interface FindAiDiagnosisPort {
     AiDiagnosis findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId);
}
