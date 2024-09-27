package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.FindReportUseCase;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.UpdateReportUseCase;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.FindReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.UpdateReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.CreateReportUseCase;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.CreateReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordService implements CreateReportUseCase, UpdateReportUseCase, FindReportUseCase {
    private final CreateReportPort createReportPort;
    private final UpdateReportPort updateReportPort;
    private final FindReportPort findReportPort;

    @Override
    public void createReport(CreateReportRequestDto createReportRequestDto) {
        log.info("Service Create record : {}", createReportRequestDto);
        createReportPort.createReport(createReportRequestDto);
    }

    @Override
    public void updateReport(UpdateReportRequestDto updateReportRequestDto) {
        log.info("Service Update record : {}", updateReportRequestDto);
        updateReportPort.updateReport(updateReportRequestDto);
    }

    @Override
    public MedicalRecord findMedicalRecordById(Long recordId) {
        log.info("Service Find record : {}", recordId);
        return findReportPort.findMedicalRecordById(recordId);
    }
}
