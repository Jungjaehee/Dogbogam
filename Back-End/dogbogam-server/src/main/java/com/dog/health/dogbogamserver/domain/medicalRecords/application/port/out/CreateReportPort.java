package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.dto.CreateReportDto;

public interface CreateReportPort {
    void createReport(CreateReportDto createReportDto);
}
