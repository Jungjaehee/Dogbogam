package com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindAllInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindDetailInsurancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class InsurancePersistenceAdapter implements FindAllInsurancePort, FindDetailInsurancePort {

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

    @Override
    public Map<String, Object> findByInsuranceId(Long insuranceId){
        insuranceRepository.findByInsuranceId(insuranceId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 보험이 존재하지 않습니다."));

        List<InsuranceBenefitEntity> insuranceBenefits = insuranceBenefitRepository.findByInsurance_InsuranceId(insuranceId);

        return insuranceBenefitMapper.insuranceBenefitEntityListToDomain(insuranceBenefits);
    }

}
