package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.dto.CreateReportDto;

public interface CreateReportUseCase {
    void createReport(CreateReportDto createReportDto);
}
