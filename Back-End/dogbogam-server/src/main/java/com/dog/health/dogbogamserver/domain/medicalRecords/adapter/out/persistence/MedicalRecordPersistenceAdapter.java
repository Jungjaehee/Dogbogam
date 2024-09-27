package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.FindReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.UpdateReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.CreateReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordPersistenceAdapter implements CreateReportPort, UpdateReportPort, FindReportPort {
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
    public Optional<MedicalRecord> findMedicalRecordById(Long recordId) {
        MedicalRecordEntity medicalRecordEntity = jpaRepository.findById(recordId)
                .orElse(null);
        return medicalRecordMapper.toDomain(medicalRecordEntity);
    }
}
