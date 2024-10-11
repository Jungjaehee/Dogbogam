package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

import java.util.List;

public interface FindReportsPort {
    List<MedicalRecord> findReportsByDogId(Long dogId);
}
