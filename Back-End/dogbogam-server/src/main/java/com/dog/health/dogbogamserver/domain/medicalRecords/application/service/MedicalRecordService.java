package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.UpdateReportUseCase;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.UpdateReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.CreateReportUseCase;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.CreateReportPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordService implements CreateReportUseCase, UpdateReportUseCase {
    private final CreateReportPort createReportPort;
    private final UpdateReportPort updateReportPort;

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
}
