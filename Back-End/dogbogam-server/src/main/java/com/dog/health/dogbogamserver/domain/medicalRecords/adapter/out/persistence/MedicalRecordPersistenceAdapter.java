package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordPersistenceAdapter implements CreateReportPort, UpdateReportPort, FindReportPort,
        DeleteReportPort, FindReportsPort {
    private final MedicalRecordSpringDataRepository jpaRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    @Transactional
    public void createReport(MedicalRecordEntity medicalRecordEntity) {
        log.info("Adapter 진료 기록 등록 : {}", medicalRecordEntity);
        jpaRepository.save(medicalRecordEntity);
    }

    @Override
    @Transactional
    public void updateReport(MedicalRecordEntity medicalRecordEntity) {
        log.info("Adapter 진료 기록 수정 : {}", medicalRecordEntity);
        jpaRepository.save(medicalRecordEntity);
    }

    @Override
    public Optional<MedicalRecord> findMedicalRecordById(Long reportId) {
        MedicalRecordEntity medicalRecordEntity = jpaRepository.findById(reportId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트 입니다."));
        return Optional.ofNullable(medicalRecordMapper.toDomain(medicalRecordEntity));
    }

    @Override
    @Transactional
    public void deleteReport(Long reportId) {
        MedicalRecordEntity medicalRecordEntity = jpaRepository.findById(reportId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트 입니다."));
        jpaRepository.delete(medicalRecordEntity);
    }

    @Override
    public Optional<List<MedicalRecord>> findReportsByDogId(Long dogId) {
        log.info("Adapter Find records : {}", dogId);

        // 레포지토리에서 reportId로 MedicalRecordEntity 리스트 조회
        List<MedicalRecordEntity> medicalRecordEntities = jpaRepository.findAllByDog_DogId(dogId);

        // 조회된 리스트가 비어있으면 Optional.empty() 반환
        if (medicalRecordEntities.isEmpty()) {
            return Optional.empty();
        }

        // MedicalRecordEntity 리스트를 MedicalRecord 도메인 리스트로 변환
        List<MedicalRecord> medicalRecords = medicalRecordEntities.stream()
                .map(medicalRecordMapper::toDomain) // mapper를 사용하여 엔티티를 도메인 객체로 변환
                .collect(Collectors.toList());

        // Optional로 감싸서 반환
        return Optional.of(medicalRecords);
    }
}
