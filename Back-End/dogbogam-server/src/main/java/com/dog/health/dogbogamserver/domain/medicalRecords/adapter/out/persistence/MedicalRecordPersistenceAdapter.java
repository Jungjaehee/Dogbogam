package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.dto.CreateReportDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.CreateReportPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MedicalRecordPersistenceAdapter implements CreateReportPort {
    private final MedicalRecordSpringDataRepository jpaRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public void createReport(CreateReportDto createReportDto) {

    }
}
