package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;

public interface CreateAiDiagnosisUseCase {
    void createAiDiagnosis(Long memberId, CreateAiDiagnosisRequestDto requestDto);
}
