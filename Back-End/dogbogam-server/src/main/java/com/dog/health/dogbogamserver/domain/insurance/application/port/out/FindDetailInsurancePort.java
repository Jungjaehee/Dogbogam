package com.dog.health.dogbogamserver.domain.insurance.application.port.out;

import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FindDetailInsurancePort {

    Optional<Insurance> existInsuranceById(Long insuranceId);
    List<InsuranceBenefit> findByInsuranceId(Long insuranceId);

}
