package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;

import java.io.IOException;

public interface UpdateReportUseCase {
    void updateReport(UpdateReportRequestDto updateReportRequestDto) throws IOException;
}
