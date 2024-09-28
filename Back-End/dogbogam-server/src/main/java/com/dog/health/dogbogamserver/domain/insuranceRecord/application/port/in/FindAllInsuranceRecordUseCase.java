package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;

import java.util.Map;

public interface FindAllInsuranceRecordUseCase {
    Map<String, Object> findAllInsuranceRecord(Long dogId, int size, int page);
}
