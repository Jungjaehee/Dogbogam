package com.dog.health.dogbogamserver.domain.insurances.application.service;

import com.dog.health.dogbogamserver.domain.insurances.application.port.in.SearchInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurances.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService implements SearchInsuranceUseCase {

    private final SearchInsurancePort insurancePort;

    @Override
    public InsuranceBenefit search(String benefit) {

        InsuranceBenefit insuranceBenefit = insurancePort.findByBenefit(benefit)
                .orElseThrow(() -> new IllegalArgumentException("해당되는 보장 혜택이 존재하지 않습니다."));

        return insuranceBenefit;
    }
}
