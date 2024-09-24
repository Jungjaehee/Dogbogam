package com.dog.health.dogbogamserver.domain.insurances.application.port.in;

import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;

public interface SearchInsuranceUseCase {

    InsuranceBenefit search(String benefit);

}
