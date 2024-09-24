package com.dog.health.dogbogamserver.domain.insurances.application.port.out;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;

public interface SearchInsurancePort {
    InsuranceBenefit searchBenefit(String cond);
}
