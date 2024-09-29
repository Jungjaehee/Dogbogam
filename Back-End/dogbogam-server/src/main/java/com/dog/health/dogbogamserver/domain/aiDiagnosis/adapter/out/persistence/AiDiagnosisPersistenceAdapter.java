package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.CreateAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosesPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AiDiagnosisPersistenceAdapter implements CreateAiDiagnosisPort, FindAiDiagnosisPort, FindAiDiagnosesPort {
    private final AiDiagnosisMapper aiDiagnosisMapper;
    private final AiDiagnosisSpringDataRepository jpaRepository;
    private final DogPersistenceAdapter dogPersistenceAdapter;
    private final DogMapper dogMapper;

    @Override
    @Transactional
    public void createAiDiagnosis(Long memberId, CreateAiDiagnosisRequestDto requestDto) {
        Dog dog = dogPersistenceAdapter.findByDogId(
                requestDto.getDogId()).orElseThrow(()->new CustomException(ErrorCode.DOG_NOT_FOUND));

        if(!dog.getMember().getMemberId().equals(memberId)){
            throw new CustomException(ErrorCode.DOG_NO_ACCESS);
        }

        AiDiagnosisEntity aiDiagnosisEntity = AiDiagnosisEntity.builder()
                .dog(dogMapper.toEntity(dog))
                .normal(requestDto.getNormal())
                .diagnosisItem(requestDto.getDiagnosisItem())
                // 이미지
                .build();
        jpaRepository.save(aiDiagnosisEntity);
    }

    @Override
    public AiDiagnosis findAiDiagnosisByAiDiagnosisId(Long aiDiagnosisId) {
        log.info("AI diagnosis aiDiagnosisId: {}", aiDiagnosisId);
        AiDiagnosisEntity aiDiagnosisEntity = jpaRepository.findById(aiDiagnosisId)
                .orElseThrow(()->new CustomException(ErrorCode.AI_DIAGNOSIS_NOT_FOUND));
        log.info("AI diagnosis aiDiagnosisEntity: {}", aiDiagnosisEntity);
        return aiDiagnosisMapper.toDomain(aiDiagnosisEntity);
    }

    @Override
    public List<AiDiagnosis> findAiDiagnosesByDogId(Long dogId) {
        return Optional.empty();
    }
}
