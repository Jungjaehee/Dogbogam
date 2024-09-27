package com.dog.health.dogbogamserver.domain.insurance.application.port.out;

import java.util.List;
import java.util.Map;

public interface FindAllInsurancePort {

    List<Map<String, Object>> findAll();
}
