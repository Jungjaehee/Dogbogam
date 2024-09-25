package com.dog.health.dogbogamserver.domain.insurances.application.port.out;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;

import java.util.List;
import java.util.Map;


public interface SearchInsurancePort {
    List<Map<String, Object>> findByBenefit(List<String> benefit);
}
