package com.dog.health.dogbogamserver.domain.insurance.application.service;

import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindAllInsuranceBenefitUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindAllInsuranceUseCase;

import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindDetailInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.SearchInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindAllInsuranceBenefitPort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindAllInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindDetailInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.SearchInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceService implements FindAllInsuranceUseCase, FindDetailInsuranceUseCase, SearchInsuranceUseCase, FindAllInsuranceBenefitUseCase {

    private final FindDetailInsurancePort findDetailInsurancePort;
    private final FindAllInsurancePort findAllInsurancePort;
    private final SearchInsurancePort searchInsurancePort;
    private final FindAllInsuranceBenefitPort findAllInsuranceBenefitPort;

    @Override
    public Map<Long, Map<String, Object>> findAll() {
        List<InsuranceBenefit> insuranceBenefits = findAllInsurancePort.findAllInsurance();

        return benefitListToInsuranceInfoList(insuranceBenefits);
    }

    @Override
    public Map<Long, Map<String, Object>> findDetailByInsuranceId(Long insuranceId){
        findDetailInsurancePort.existInsuranceById(insuranceId)
                .orElseThrow(()-> new IllegalArgumentException("해당 보험이 존재하지 않습니다."));

        List<InsuranceBenefit> insuranceBenefits = findDetailInsurancePort.findByInsuranceId(insuranceId);
        return benefitListToInsuranceInfoList(insuranceBenefits);
    }

    @Override
    public Map<Long, Map<String, Object>> search(List<String> benefit) {
        if(benefit.size() == 0)
            throw new IllegalArgumentException("선택된 보장 혜택이 존재하지 않습니다.");

        List<InsuranceBenefit> insuranceBenefits = searchInsurancePort.findByBenefit(benefit);

        return benefitListToInsuranceInfoList(insuranceBenefits);
    }

    @Override
    public List<String> findAllBenefits(){
        List<InsuranceBenefit> insuranceBenefits = findAllInsuranceBenefitPort.findAllBenefits();
        Set<String> benefitSet = new HashSet<>(insuranceBenefits.size());

        for(InsuranceBenefit benefit : insuranceBenefits){
            benefitSet.add(benefit.getBenefit());
        }

        return new ArrayList<>(benefitSet);
    }

    private Map<Long, Map<String, Object>> benefitListToInsuranceInfoList(List<InsuranceBenefit> insuranceBenefitList){
        Map<Long, Map<String, Object>> insuranceBenfitMap = new HashMap();

        for(InsuranceBenefit benefitDomain : insuranceBenefitList){
            Long key = benefitDomain.getInsurance().getInsuranceId();
            if(!insuranceBenfitMap.containsKey(key)){
                insuranceBenfitMap.put(key, new HashMap<>());
                insuranceBenfitMap.get(key).put("insurance", benefitDomain.getInsurance());
                insuranceBenfitMap.get(key).put("benefit", new ArrayList<String>());
            }

            List<String> benefits = (List<String>) insuranceBenfitMap.get(key).get("benefit");
            benefits.add(benefitDomain.getBenefit());

        }

        return insuranceBenfitMap;

    }

}
