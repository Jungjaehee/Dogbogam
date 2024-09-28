package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogSpringDataRepository;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence.InsuranceMapper;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.SaveInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence.InsuranceSpringDataRepository;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InsuranceRecordPersistenceAdapter implements SaveInsuranceRecordPort {

    private final InsuranceRecordMapper insuranceRecordMapper;
    private final InsuranceMapper insuranceMapper;
    private final DogMapper dogMapper;
    private final InsuranceRecordSpringDataRepository insuranceRecordRepository;
    private final DogSpringDataRepository dogRepository;
    private final InsuranceSpringDataRepository insuranceRepository;

    @Override
    public void save(InsuranceRecord insuranceRecord) {
        InsuranceRecordEntity insuranceRecordEntity = insuranceRecordMapper.toEntity(insuranceRecord);
        insuranceRecordRepository.save(insuranceRecordEntity);
    }

    @Override
    public Optional<InsuranceRecord> checkExistingInsuranceRecord(Long dogId, Long insuranceId){
        return insuranceRecordMapper.toOptionalDomain(insuranceRecordRepository.findByInsurance_InsuranceIdAndDog_DogId(insuranceId, dogId));
    }

    @Override
    public Optional<Insurance> checkExistingInsurance(Long insuranceId){
        return insuranceMapper.toOptionalDomain(insuranceRepository.findByInsuranceId(insuranceId));
    }

    @Override
    public Dog checkExistingDog(Long dogId){
        DogEntity dogEntity = dogRepository.findById(dogId).orElseThrow(() -> new IllegalArgumentException("해당 반려견이 존재하지 않습니다."));
        return dogMapper.toDomain(dogEntity);
    }

}
