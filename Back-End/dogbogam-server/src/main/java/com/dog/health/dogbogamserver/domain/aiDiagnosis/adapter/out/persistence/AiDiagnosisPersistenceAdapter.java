package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.CreateAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiDiagnosisPersistenceAdapter implements CreateAiDiagnosisPort {
    private final AiDiagnosisMapper aiDiagnosisMapper;
    private final AiDiagnosisSpringDataRepository jpaRepository;
    private final DogPersistenceAdapter dogPersistenceAdapter;

    @Override
    @Transactional
    public void createAiDiagnosis(CreateAiDiagnosisRequestDto requestDto) {
        AiDiagnosisEntity aiDiagnosisEntity = AiDiagnosisEntity.builder()
                .dog(dogPersistenceAdapter.findEntityByDogId(requestDto.getDogId())
                        .orElseThrow(() -> new CustomException(ErrorCode.DOG_NOT_FOUND)))
                .normal(requestDto.getNormal())
                .diagnosisItem(requestDto.getDiagnosisItem())
                // 이미지
                .build();
        jpaRepository.save(aiDiagnosisEntity);
    }
}
