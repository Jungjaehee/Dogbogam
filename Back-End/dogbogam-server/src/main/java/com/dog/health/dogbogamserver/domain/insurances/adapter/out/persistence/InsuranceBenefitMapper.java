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

    public List<InsuranceBenefit> entityListToDomain(List<InsuranceBenefitEntity> entityList){
        List<InsuranceBenefit> insuranceBenefitList = new ArrayList<>();
        for (InsuranceBenefitEntity insuranceBenefitEntity : entityList){
            insuranceBenefitList.add(entityToDomain(insuranceBenefitEntity));
        }

        return insuranceBenefitList;
    }

}
