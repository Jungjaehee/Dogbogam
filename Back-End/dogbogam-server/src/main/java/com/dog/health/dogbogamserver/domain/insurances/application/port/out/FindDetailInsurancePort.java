package com.dog.health.dogbogamserver.domain.insurances.application.port.out;

import java.util.Map;

public interface FindDetailInsurancePort {

    Map<String, Object> findByInsuranceId(Long insuranceId);

}
