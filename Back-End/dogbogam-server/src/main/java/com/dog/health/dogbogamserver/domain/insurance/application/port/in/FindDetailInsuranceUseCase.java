package com.dog.health.dogbogamserver.domain.insurance.application.port.in;

import java.util.Map;

public interface FindDetailInsuranceUseCase {

    Map<Long, Map<String, Object>> findDetailByInsuranceId(Long insuranceId);
}
