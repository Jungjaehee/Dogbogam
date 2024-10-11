package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateMedicalReportRequestDto;

import java.io.IOException;

public interface UpdateReportUseCase {
    void updateReport(UpdateMedicalReportRequestDto updateMedicalReportRequestDto) throws IOException;
}
