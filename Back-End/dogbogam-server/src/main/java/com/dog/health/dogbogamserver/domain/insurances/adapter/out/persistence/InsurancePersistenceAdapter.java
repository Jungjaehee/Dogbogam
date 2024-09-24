package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InsurancePersistenceAdapter implements SearchInsurancePort {

    private final InsuranceBenefitMapper insuranceBenefitMapper;
    private final InsuranceBenefitSpringDataRepository insuranceBenefitRepository;

    @Override
    public List<InsuranceBenefit> findByBenefit(String benefit){
        List<InsuranceBenefitEntity> insurances = insuranceBenefitRepository.findByBenefitContains(benefit);

        return insuranceBenefitMapper.entityListToDomainList(insurances);
    }

}
