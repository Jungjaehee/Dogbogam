package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.response.FindMedicalReportResponseDto;

public interface FindReportUseCase {
    FindMedicalReportResponseDto findMedicalRecordById(Long recordId);
}
