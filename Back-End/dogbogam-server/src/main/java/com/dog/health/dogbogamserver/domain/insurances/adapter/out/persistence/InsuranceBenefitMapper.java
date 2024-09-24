package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<InsuranceBenefit> entityListToDomainList(List<InsuranceBenefitEntity> entityList){
        List<InsuranceBenefit> insuranceBenefitList = new ArrayList<>();
        for(InsuranceBenefitEntity entity : entityList){
            insuranceBenefitList.add(entityToDomain(entity));
        }
        return insuranceBenefitList;
    }

}
