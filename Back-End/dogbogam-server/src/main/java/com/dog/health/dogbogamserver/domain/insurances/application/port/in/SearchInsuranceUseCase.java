package com.dog.health.dogbogamserver.domain.insurances.application.port.in;

import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;

import java.util.List;

public interface SearchInsuranceUseCase {

    List<InsuranceBenefit> search(String benefit);

}
