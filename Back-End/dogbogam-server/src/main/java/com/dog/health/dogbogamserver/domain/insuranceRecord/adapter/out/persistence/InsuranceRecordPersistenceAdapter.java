package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.LoadInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.PageableLoadInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.SaveInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InsuranceRecordPersistenceAdapter implements SaveInsuranceRecordPort, LoadInsuranceRecordPort,
        PageableLoadInsuranceRecordPort {

    private final InsuranceRecordMapper insuranceRecordMapper;
    private final InsuranceRecordSpringDataRepository insuranceRecordRepository;

    @Transactional
    @Override
    public void save(InsuranceRecord insuranceRecord) {
        InsuranceRecordEntity insuranceRecordEntity = insuranceRecordMapper.toEntity(insuranceRecord);
        insuranceRecordRepository.save(insuranceRecordEntity);
    }

    @Override
    public Optional<InsuranceRecord> checkExistingInsuranceRecord(Long dogId, Long insuranceId){
        Optional<InsuranceRecordEntity> insuranceRecordEntity =
                insuranceRecordRepository.findByInsurance_InsuranceIdAndDog_DogId(insuranceId, dogId);
        return insuranceRecordEntity.map(insuranceRecordMapper::toDomain);
    }

    @Override
    public Optional<InsuranceRecord> loadInsuranceRecord(Long insuranceRecordId){
        Optional<InsuranceRecordEntity> insuranceRecordEntity = insuranceRecordRepository.findById(insuranceRecordId);
        return insuranceRecordEntity.map(insuranceRecordMapper::toDomain);
    }

    @Override
    public Page<InsuranceRecord> findAllInsuranceRecords(Long dogId, int size, int page){
        Pageable pageable = PageRequest.of(--page, size);
        Page<InsuranceRecordEntity> insuranceRecordEntityPage = insuranceRecordRepository
                .findAllByDog_DogIdOrderByRegistDate(dogId, pageable);

        return insuranceRecordEntityPage.map(insuranceRecordMapper::toDomain);
    }
}
