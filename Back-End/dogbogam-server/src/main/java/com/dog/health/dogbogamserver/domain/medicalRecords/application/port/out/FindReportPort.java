package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

public interface FindReportPort {
    MedicalRecord findMedicalRecordById(Long recordId);
}
