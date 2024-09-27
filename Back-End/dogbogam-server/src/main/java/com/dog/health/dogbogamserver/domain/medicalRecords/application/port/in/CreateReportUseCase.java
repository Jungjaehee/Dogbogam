package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;

public interface CreateReportUseCase {
    void createReport(CreateReportRequestDto createReportRequestDto);
}
