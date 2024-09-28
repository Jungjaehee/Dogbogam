package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence.InsuranceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public Optional<InsuranceRecord> toOptionalDomain(Optional<InsuranceRecordEntity> insuranceRecordEntity){
        return insuranceRecordEntity.map(e -> new InsuranceRecord(e.getInsuranceRecordId(),
                insuranceMapper.toDomain(e.getInsurance()),
                dogMapper.toDomain(e.getDog()),
                e.getRegistDate(),
                e.getMonthlyPayment(),
                e.getExpirationDate(),
                e.isDeleted(),
                e.getCreatedAt(),
                e.getModifiedAt()));
    }

    public InsuranceRecordEntity toEntity(InsuranceRecord insuranceRecord){
        return new InsuranceRecordEntity(
                insuranceRecord.getInsuranceRecordId(),
                insuranceMapper.toEntity(insuranceRecord.getInsurance()),
                dogMapper.toEntity(insuranceRecord.getDog()),
                insuranceRecord.getRegistDate(),
                insuranceRecord.getMonthlyPayment(),
                insuranceRecord.getExpirationDate(),
                false
        );
    }
}
