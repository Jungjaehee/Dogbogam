package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.application.port.out.FindAllInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.application.port.out.FindDetailInsurancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.NoHandlerFoundException;

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
        InsuranceEntity insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("요청한 보험이 존재하지 않습니다."));

        List<InsuranceBenefitEntity> insuranceBenefits = insuranceBenefitRepository.findByInsuranceId(insuranceId);

        return insuranceBenefits.insuranceBenefitEntityToDomain(insuranceBenefits);
    }

}
