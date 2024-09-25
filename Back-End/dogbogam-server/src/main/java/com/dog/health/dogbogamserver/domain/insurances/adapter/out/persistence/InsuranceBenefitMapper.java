package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InsuranceBenefitMapper {

    private final InsuranceMapper insuranceMapper;

    public List<Map<String, Object>> insuranceEntityAndBenefitsToDomainList(List<InsuranceEntity> entityList, List<List<String>> benefits){
        List<Map<String, Object>> insuranceInfo = new ArrayList<>();

        int i = 0;
        for (InsuranceEntity insuranceEntity : entityList){
            Map<String, Object> insuranceMap = new HashMap<>();
            insuranceMap.put("insurance", insuranceMapper.toDomain(insuranceEntity));
            insuranceMap.put("benefits", benefits.get(i++));

            insuranceInfo.add(insuranceMap);
        }

        return insuranceInfo;
    }

    public Map<String, Object> insuranceBenefitEntityListToDomain(List<InsuranceBenefitEntity> entityList){

        Map<String, Object> insuranceBenfitListDomain = new HashMap<>();
        insuranceBenfitListDomain.put("insurance", insuranceMapper.toDomain(entityList.get(0).getInsurance()));

        List<String> benefits = new ArrayList<>();
        for (InsuranceBenefitEntity insuranceBenefitEntity : entityList){
            benefits.add(insuranceBenefitEntity.getBenefit());
        }

        insuranceBenfitListDomain.put("benefits",benefits);

        return insuranceBenfitListDomain;
    }

}
