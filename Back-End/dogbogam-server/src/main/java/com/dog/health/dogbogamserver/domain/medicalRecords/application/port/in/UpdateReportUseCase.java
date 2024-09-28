package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;

public interface UpdateReportUseCase {
    void updateReport(UpdateReportRequestDto updateReportRequestDto);
}
