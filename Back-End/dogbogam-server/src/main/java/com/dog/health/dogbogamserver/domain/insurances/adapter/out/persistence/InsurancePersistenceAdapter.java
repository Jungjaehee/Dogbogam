package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.application.port.out.FindAllInsurancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class InsurancePersistenceAdapter implements FindAllInsurancePort {

    private final InsuranceSpringDataRepository insuranceRepository;
    private final InsuranceBenefitMapper insuranceBenefitMapper;
    private final InsuranceBenefitSpringDataRepository insuranceBenefitRepository;

    @Override
    public List<Map<String, Object>> findAll(){
        List<InsuranceEntity> insurances = insuranceRepository.findAll();
        List<List<String>> insuranceBenefits = new ArrayList<>();
        for (InsuranceEntity insurance : insurances) {
            List<String> benefits = insuranceBenefitRepository.findBenefitByInsuranceId(insurance.getInsuranceId());
            insuranceBenefits.add(benefits);
        }

        return insuranceBenefitMapper.insuranceEntityAndBenefitsToDomainList(insurances, insuranceBenefits);
    }

}
