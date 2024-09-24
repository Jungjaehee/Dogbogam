package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

    public InsuranceBenefit toInsuranceBenefitDomain(InsuranceBenefitEntity entity){
        return new InsuranceBenefit(
                entity.getInsuranceBenefitId(),
                toInsuranceDomain(entity.getInsurance()),
                entity.getBenefit()
        );
    }

    public Insurance toInsuranceDomain(InsuranceEntity entity){
        return new Insurance(
                entity.getInsuranceId(),
                entity.getName(),
                entity.getCompany(),
                entity.getMinAge(),
                entity.getMaxAge(),
                entity.getFee(),
                entity.getLimit(),
                entity.getPeriod(),
                entity.getDescription(),
                entity.getCoverageRatio()
        );
    }

}
