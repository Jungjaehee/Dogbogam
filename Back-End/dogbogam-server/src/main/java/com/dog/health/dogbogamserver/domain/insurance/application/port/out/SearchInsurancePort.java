package com.dog.health.dogbogamserver.domain.insurance.application.port.out;

import java.util.List;
import java.util.Map;


public interface SearchInsurancePort {
    List<Map<String, Object>> findByBenefit(List<String> benefit);
}
