package com.dog.health.dogbogamserver.domain.insurances.application.port.in;

import java.util.List;
import java.util.Map;

public interface FindAllInsuranceUseCase {

    List<Map<String, Object>> findAll();

}
