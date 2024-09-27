package com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;
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

    public InsuranceBenefit entityToDomain(InsuranceBenefitEntity entity){
        return new InsuranceBenefit(
                entity.getInsuranceBenefitId(),
                insuranceMapper.toDomain(entity.getInsurance()),
                entity.getBenefit()
        );
    }

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

    public List<InsuranceBenefit> entityListToDomainList(List<InsuranceBenefitEntity> entityList){
        List<InsuranceBenefit> insuranceBenefitList = new ArrayList<>();
        for (InsuranceBenefitEntity insuranceBenefitEntity : entityList){
            insuranceBenefitList.add(entityToDomain(insuranceBenefitEntity));
        }

        return insuranceBenefitList;
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
