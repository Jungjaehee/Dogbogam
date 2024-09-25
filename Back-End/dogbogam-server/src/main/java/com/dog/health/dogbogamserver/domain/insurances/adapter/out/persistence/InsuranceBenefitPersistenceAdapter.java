package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.application.port.out.FindAllInsuranceBenefitPort;
import com.dog.health.dogbogamserver.domain.insurances.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class InsuranceBenefitPersistenceAdapter implements SearchInsurancePort, FindAllInsuranceBenefitPort {

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

    @Override
    public List<InsuranceBenefit> findAllBenefits(){
        return insuranceBenefitMapper.entityListToDomain(insuranceBenefitRepository.findAll());
    }

}
