package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

    public Insurance toDomain(InsuranceEntity entity){
        return new Insurance(
                entity.getInsuranceId(),
                entity.getName(),
                entity.getCompany(),
                entity.getMinAge(),
                entity.getMaxAge(),
                entity.getFee(),
                entity.getLimitFee(),
                entity.getPeriod(),
                entity.getDescription(),
                entity.getCoverageRatio(),
                entity.getS3ImageName(),
                entity.getS3ImageUrl()
        );
    }

}
