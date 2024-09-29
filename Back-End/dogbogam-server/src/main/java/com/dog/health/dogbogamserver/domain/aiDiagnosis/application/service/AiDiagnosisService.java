package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.CreateAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.CreateAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiDiagnosisService implements CreateAiDiagnosisUseCase {

    private final CreateAiDiagnosisPort createAiDiagnosisPort;

    @Override
    public void createAiDiagnosis(CreateAiDiagnosisRequestDto requestDto) {
        createAiDiagnosisPort.createAiDiagnosis(requestDto);
    }
}
