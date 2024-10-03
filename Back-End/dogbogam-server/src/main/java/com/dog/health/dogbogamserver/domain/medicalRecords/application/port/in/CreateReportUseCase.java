package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;

import java.io.IOException;

public interface CreateReportUseCase {
    void createReport(CreateReportRequestDto createReportRequestDto) throws IOException;
}
