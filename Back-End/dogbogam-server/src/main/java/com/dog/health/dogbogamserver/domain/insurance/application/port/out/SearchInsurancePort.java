package com.dog.health.dogbogamserver.domain.insurance.application.port.out;

import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;

import java.util.List;
import java.util.Map;


public interface SearchInsurancePort {
    List<InsuranceBenefit> findByBenefit(List<String> benefit);
}
