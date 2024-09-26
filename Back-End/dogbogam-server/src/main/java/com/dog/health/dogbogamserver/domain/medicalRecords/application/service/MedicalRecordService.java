package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.dto.CreateReportDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.CreateReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalRecordService implements CreateReportUseCase {
    private MedicalRecordPersistenceAdapter medicalRecordPersistenceAdapter;

    @Override
    public void createReport(CreateReportDto createReportDto) {
        medicalRecordPersistenceAdapter.createReport(createReportDto);
    }
}
