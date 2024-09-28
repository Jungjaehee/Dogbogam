package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

import java.util.List;
import java.util.Optional;

public interface FindReportsPort {
    Optional<List<MedicalRecord>> findReportsByDogId(Long dogId);
}
