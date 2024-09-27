package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

import java.util.Optional;

public interface FindReportPort {
    Optional<MedicalRecord> findMedicalRecordById(Long recordId);
}
