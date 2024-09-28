package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out;

import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import org.springframework.data.domain.Page;

public interface PageableLoadInsuranceRecordPort {

    Page<InsuranceRecord> findAllInsuranceRecords(Long dogId, int size, int page);

}
