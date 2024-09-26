package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecord getMedicalRecordById(Long id);
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> getAllMedicalRecordsForDog(Long dogId);
}
