package com.dog.health.dogbogamserver.domain.insurances.application.port.out;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;

import java.util.Optional;

public interface SearchInsurancePort {
    Optional<InsuranceBenefit> findByBenefit(String Benefit);
}
