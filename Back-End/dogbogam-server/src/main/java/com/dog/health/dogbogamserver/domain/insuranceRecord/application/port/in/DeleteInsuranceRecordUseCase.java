package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;

import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;

public interface DeleteInsuranceRecordUseCase {
    void deleteInsuranceRecord(Long insuranceRecordId);
}
