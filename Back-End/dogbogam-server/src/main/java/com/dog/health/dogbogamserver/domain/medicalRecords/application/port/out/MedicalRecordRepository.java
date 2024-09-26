package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

import java.util.Optional;
import java.util.List;

public interface MedicalRecordRepository {
    MedicalRecord save(MedicalRecord medicalRecord);
    Optional<MedicalRecord> findById(Long id);
    List<MedicalRecord> findAllByDog_DogId(Long dogId);
}
