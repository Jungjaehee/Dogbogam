package com.dog.health.dogbogamserver.domain.insurance.application.port.in;

import java.util.List;

public interface FindAllInsuranceBenefitUseCase {

    List<String> findAllBenefits();

}
