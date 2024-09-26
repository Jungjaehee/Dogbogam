package com.dog.health.dogbogamserver.domain.insurances.application.port.in;

import java.util.Map;

public interface FindDetailInsuranceUseCase {

    Map<String, Object> findDetailByInsuranceId(Long insuranceId);
}
