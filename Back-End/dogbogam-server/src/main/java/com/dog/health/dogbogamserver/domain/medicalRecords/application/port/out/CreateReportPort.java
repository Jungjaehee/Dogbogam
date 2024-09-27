package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;

public interface CreateReportPort {
    void createReport(CreateReportRequestDto createReportRequestDto);
}
