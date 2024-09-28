package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;

import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;

public interface FindInsuranceRecordUseCase {

    InsuranceRecord findInsuranceRecordById(Long insuranceRecordId);

}
