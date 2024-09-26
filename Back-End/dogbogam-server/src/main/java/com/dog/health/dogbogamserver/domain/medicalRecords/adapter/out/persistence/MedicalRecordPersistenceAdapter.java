package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.MedicalRecordRepository;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MedicalRecordPersistenceAdapter implements MedicalRecordRepository {
    private final MedicalRecordSpringDataRepository jpaRepository;

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        MedicalRecordEntity entity = MedicalRecordMapper.toEntity(medicalRecord);
        MedicalRecordEntity savedEntity = jpaRepository.save(entity);
        return MedicalRecordMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<MedicalRecord> findById(Long id) {
        return jpaRepository.findById(id)
                .map(MedicalRecordMapper::toDomain);
    }

    @Override
    public List<MedicalRecord> findAllByDogId(Long dogId) {
        return jpaRepository.findAllByDogId(dogId)
                .stream()
                .map(MedicalRecordMapper::toDomain)
                .collect(Collectors.toList());
    }
}
