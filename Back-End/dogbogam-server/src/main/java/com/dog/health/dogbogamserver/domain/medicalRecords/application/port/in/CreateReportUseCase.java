package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateMedicalReportRequestDto;

import java.io.IOException;

public interface CreateReportUseCase {
    void createReport(CreateMedicalReportRequestDto createMedicalReportRequestDto) throws IOException;
}
