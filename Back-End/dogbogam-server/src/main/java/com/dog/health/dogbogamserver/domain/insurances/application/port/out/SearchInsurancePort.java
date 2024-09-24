package com.dog.health.dogbogamserver.domain.insurances.application.port.out;

import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;

import java.util.List;


public interface SearchInsurancePort {
    List<InsuranceBenefit> findByBenefit(String benefit);
}
