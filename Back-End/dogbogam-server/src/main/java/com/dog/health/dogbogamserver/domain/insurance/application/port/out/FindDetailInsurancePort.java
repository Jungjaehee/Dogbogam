package com.dog.health.dogbogamserver.domain.insurance.application.port.out;

import java.util.Map;

public interface FindDetailInsurancePort {

    Map<String, Object> findByInsuranceId(Long insuranceId);

}
