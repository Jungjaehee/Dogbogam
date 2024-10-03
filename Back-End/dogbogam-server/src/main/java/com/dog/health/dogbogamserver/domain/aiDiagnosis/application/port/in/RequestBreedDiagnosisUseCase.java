package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.DiagnosisRequestDto;

import java.io.IOException;
import java.util.Map;

public interface RequestBreedDiagnosisUseCase {

    Map<String, Long> requestBreedDiagnosis(Long memberId, DiagnosisRequestDto requestDto)  throws IOException;

}
