package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

public interface FindReportUseCase {
    MedicalRecord findMedicalRecordById(Long recordId);
}
