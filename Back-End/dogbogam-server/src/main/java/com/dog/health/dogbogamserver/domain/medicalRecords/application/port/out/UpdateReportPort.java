package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;

public interface UpdateReportPort {
    void updateReport(UpdateReportRequestDto updateReportRequestDto);
}