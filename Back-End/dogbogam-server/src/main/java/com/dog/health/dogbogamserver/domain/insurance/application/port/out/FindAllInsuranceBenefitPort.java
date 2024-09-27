package com.dog.health.dogbogamserver.domain.insurance.application.port.out;

import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;

import java.util.List;

public interface FindAllInsuranceBenefitPort {

    List<InsuranceBenefit> findAllBenefits();

}
