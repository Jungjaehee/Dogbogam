package com.dog.health.dogbogamserver.domain.insuranceRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dogs.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.insuranceRecords.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence.InsuranceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsuranceRecordMapper {

    private final InsuranceMapper insuranceMapper;
    private final DogMapper dogMapper;

    public InsuranceRecord toDomain(InsuranceRecordEntity insuranceRecordEntity){
        return new InsuranceRecord(insuranceRecordEntity.getInsuranceRecordId(),
                insuranceMapper.toDomain(insuranceRecordEntity.getInsurance()),
                dogMapper.toDomain(insuranceRecordEntity.getDog()),
                insuranceRecordEntity.getRegistDate(),
                insuranceRecordEntity.getMonthlyPayment(),
                insuranceRecordEntity.getExpirationDate(),
                insuranceRecordEntity.isDeleted(),
                insuranceRecordEntity.getCreatedAt(),
                insuranceRecordEntity.getModifiedAt());
    }

    public InsuranceRecordEntity toEntity(InsuranceRecord insuranceRecord){

    }
}
