package com.dog.health.dogbogamserver.domain.insurances.application.service;

import com.dog.health.dogbogamserver.domain.insurances.application.port.in.SearchInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurances.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService implements SearchInsuranceUseCase {

    private final SearchInsurancePort insurancePort;

    @Override
    public List<InsuranceBenefit> search(String benefit) {
        
        return insurancePort.findByBenefit(benefit);
    }
}
