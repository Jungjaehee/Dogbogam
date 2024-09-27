package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.DeleteReportPort;
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
public class MedicalRecordPersistenceAdapter implements CreateReportPort, UpdateReportPort, FindReportPort, DeleteReportPort {
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
}
