package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.MedicalRecordService;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.MedicalRecordRepository;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Record not found"));
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecordsForDog(Long dogId) {
        return medicalRecordRepository.findAllByDog_DogId(dogId);
    }
}
