package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;

import java.util.Optional;

public interface SaveInsuranceRecordPort {

    Optional<InsuranceRecord> checkExistingInsuranceRecord(Long dogId, Long insuranceRecordId);
    Optional<Insurance> checkExistingInsurance(Long insuranceId);
    Optional<Dog> checkExistingDog(Long dogId);
    void save(InsuranceRecord insuranceRecord);

}
