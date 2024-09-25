package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class InsurancePersistenceAdapter implements SearchInsurancePort {

    private final InsuranceBenefitMapper insuranceBenefitMapper;
    private final InsuranceBenefitSpringDataRepository insuranceBenefitRepository;

    @Override
    public List<Map<String, Object>> findByBenefit(List<String> benefit){
        List<InsuranceEntity> insurances = insuranceBenefitRepository.findInsuranceEntityByBenefitIn(benefit);
        List<List<String>> insuranceBenefits = new ArrayList<>();
        for (InsuranceEntity insurance : insurances) {
            List<String> benefits = insuranceBenefitRepository.findBenefitByInsuranceId(insurance.getInsuranceId());
            insuranceBenefits.add(benefits);
        }

        return insuranceBenefitMapper.insuranceEntityAndBenefitsToDomainList(insurances, insuranceBenefits);
    }

}
