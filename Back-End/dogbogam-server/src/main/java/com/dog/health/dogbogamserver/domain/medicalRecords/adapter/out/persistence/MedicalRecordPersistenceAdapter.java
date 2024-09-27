package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.CreateReportPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordPersistenceAdapter implements CreateReportPort {
    private final MedicalRecordSpringDataRepository jpaRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public void createReport(CreateReportRequestDto createReportRequestDto) {
        MedicalRecordEntity medicalRecordEntity = medicalRecordMapper.toEntity(createReportRequestDto);
        log.info("Adapter 진료 기록 등롤 : {}", medicalRecordEntity);
        jpaRepository.save(medicalRecordEntity);
    }
}
