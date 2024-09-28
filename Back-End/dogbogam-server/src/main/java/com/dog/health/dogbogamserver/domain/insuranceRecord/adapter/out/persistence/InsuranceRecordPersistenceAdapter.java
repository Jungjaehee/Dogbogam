package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogSpringDataRepository;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
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
    private final InsuranceRecordSpringDataRepository insuranceRecordRepository;
    private final DogSpringDataRepository dogRepository;
    private final InsuranceSpringDataRepository insuranceRepository;

    @Override
    public void save(InsuranceRecord insuranceRecord) {

    }

    @Override
    public Optional<InsuranceRecord> checkExistingInsuranceRecord(Long dogId, Long insuranceId){
        insuranceRecordRepository.findByInsurance_InsuranceIdAndDog_DogId(insuranceId, dogId);
    }

    @Override
    public Optional<Insurance> checkExistingInsurance(Long insuranceId){

    }

    @Override
    public Optional<Dog> checkExistingDog(Long dogId){

    }

}
