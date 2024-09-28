package com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindAllInsuranceBenefitPort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindAllInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindDetailInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InsurancePersistenceAdapter implements FindAllInsurancePort, FindDetailInsurancePort, SearchInsurancePort, FindAllInsuranceBenefitPort {

    private final InsuranceSpringDataRepository insuranceRepository;
    private final InsuranceBenefitSpringDataRepository insuranceBenefitRepository;
    private final InsuranceBenefitMapper insuranceBenefitMapper;
    private final InsuranceMapper insuranceMapper;

    @Override
    public List<InsuranceBenefit> findAllInsurance(){
        return insuranceBenefitMapper.entityListToDomainList(insuranceBenefitRepository.findAll());
    }

    @Override
    public Optional<Insurance> existInsuranceById(Long insuranceId){
        Optional<InsuranceEntity> insuranceEntity = insuranceRepository.findById(insuranceId);
        return insuranceEntity.map(insuranceMapper::toDomain);
    }

    @Override
    public List<InsuranceBenefit> findByInsuranceId(Long insuranceId){
        return insuranceBenefitMapper.entityListToDomainList(insuranceBenefitRepository.findByInsurance_InsuranceId(insuranceId));
    }

    @Override
    public List<InsuranceBenefit> findByBenefit(List<String> benefit){
        return insuranceBenefitMapper.entityListToDomainList(insuranceBenefitRepository.findByBenefitIn(benefit));
    }

    @Override
    public List<InsuranceBenefit> findAllBenefits(){
        return insuranceBenefitMapper.entityListToDomainList(insuranceBenefitRepository.findAll());
    }

}
