package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.CreateAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosesUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.CreateAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosesPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AiDiagnosisService implements CreateAiDiagnosisUseCase, FindAiDiagnosisUseCase, FindAiDiagnosesUseCase {

    private final CreateAiDiagnosisPort createAiDiagnosisPort;
    private final FindAiDiagnosisPort findAiDiagnosisPort;
    private final FindAiDiagnosesPort findAiDiagnosesPort;

    @Override
    public void createAiDiagnosis(Long memberId, CreateAiDiagnosisRequestDto requestDto) {
        createAiDiagnosisPort.createAiDiagnosis(memberId, requestDto);
    }

    @Override
    public AiDiagnosis findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId) {
        return findAiDiagnosisPort.findAiDiagnosisByAiDiagnosisId(aiDiagnosisId);
    }

    @Override
    public List<AiDiagnosis> findAiDiagnosesByDogId(Long dogId) {
        return findAiDiagnosesPort.findAiDiagnosesByDogId(dogId);
    }
}
