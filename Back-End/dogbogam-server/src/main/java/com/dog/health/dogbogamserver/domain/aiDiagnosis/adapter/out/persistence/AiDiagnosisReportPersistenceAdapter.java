package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.CreateAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.DeleteAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosesPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.FindAiDiagnosisPort;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.dog.application.service.DogService;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AiDiagnosisReportPersistenceAdapter implements CreateAiDiagnosisPort, FindAiDiagnosisPort, FindAiDiagnosesPort
, DeleteAiDiagnosisPort {
    private final AiDiagnosisMapper aiDiagnosisMapper;
    private final AiDiagnosisSpringDataRepository jpaRepository;
    private final DogPersistenceAdapter dogPersistenceAdapter;
    private final DogMapper dogMapper;
    private final DogService dogService;

    @Override
    @Transactional
    public Long createAiDiagnosis(Long memberId, CreateAiDiagnosisRequestDto requestDto) {
        Dog dog = dogPersistenceAdapter.findByDogId(
                requestDto.getDogId()).orElseThrow(()->new CustomException(ErrorCode.DOG_NOT_FOUND));

        if(!dog.getMember().getMemberId().equals(memberId)){
            throw new CustomException(ErrorCode.DOG_NO_ACCESS);
        }

        AiDiagnosisEntity aiDiagnosisEntity = AiDiagnosisEntity.builder()
                .dog(dogMapper.toEntity(dogService.FindDogByDogId(dog.getDogId())))
                .normal(requestDto.getNormal())
                .diagnosisItem(requestDto.getDiagnosisItem())
                .imageName(requestDto.getImageName())
                .imageUrl(requestDto.getImageUrl())
                .build();
        jpaRepository.save(aiDiagnosisEntity);


        return aiDiagnosisEntity.getAiDiagnosisId();
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
    public Page<AiDiagnosis> findAiDiagnosesByDogId(Long dogId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AiDiagnosisEntity> aiDiagnosesPage = jpaRepository.findByDog_DogId(dogId, pageable);

        return aiDiagnosisMapper.entityPagetoDomainPage(aiDiagnosesPage);
    }

    @Override
    @Transactional
    public void deleteAiDiagnosis(Long aiDiagnosisId) {
        jpaRepository.deleteById(aiDiagnosisId);
    }
}
