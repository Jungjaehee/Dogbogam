package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out;

import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;

import java.util.Optional;

public interface LoadInsuranceRecordPort {

    Optional<InsuranceRecord> loadInsuranceRecord(Long insuranceRecordId);
}
