package com.dog.health.dogbogamserver.domain.insuranceRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.dogs.domain.Dog;
import com.dog.health.dogbogamserver.domain.insuranceRecords.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;

import java.util.Optional;

public interface SaveInsuranceRecordPort {

    Optional<InsuranceRecord> checkExistingInsuranceRecord(Long dogId, Long insuranceRecordId);
    Optional<Insurance> checkExistingInsurance(Long insuranceId);
    Optional<Dog> checkExistingDog(Long dogId);
    void save(InsuranceRecord insuranceRecord);

}
