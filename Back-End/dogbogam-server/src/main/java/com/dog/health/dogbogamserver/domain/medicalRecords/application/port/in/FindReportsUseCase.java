package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.response.FindMedicalReportResponseDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

import java.util.List;

public interface FindReportsUseCase {
    List<FindMedicalReportResponseDto> findReportsByDogId(Long dogId);
}
