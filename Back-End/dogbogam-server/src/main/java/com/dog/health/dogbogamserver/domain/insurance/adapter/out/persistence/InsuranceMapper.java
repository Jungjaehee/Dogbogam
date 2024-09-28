package com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public Optional<Insurance> toOptionalDomain(Optional<InsuranceEntity> entity){
        return entity.map(e -> new Insurance(
                e.getInsuranceId(),
                e.getName(),
                e.getCompany(),
                e.getMinAge(),
                e.getMaxAge(),
                e.getFee(),
                e.getLimitFee(),
                e.getPeriod(),
                e.getDescription(),
                e.getCoverageRatio(),
                e.getS3ImageName(),
                e.getS3ImageUrl()
        ));
    }

    public InsuranceEntity toEntity(Insurance insurance){
        return new InsuranceEntity(
                insurance.getInsuranceId(),
                insurance.getName(),
                insurance.getCompany(),
                insurance.getMinAge(),
                insurance.getMaxAge(),
                insurance.getFee(),
                insurance.getLimit(),
                insurance.getPeriod(),
                insurance.getDescription(),
                insurance.getCoverageRatio(),
                insurance.getS3ImageName(),
                insurance.getS3ImageUrl()
        );
    }

}
