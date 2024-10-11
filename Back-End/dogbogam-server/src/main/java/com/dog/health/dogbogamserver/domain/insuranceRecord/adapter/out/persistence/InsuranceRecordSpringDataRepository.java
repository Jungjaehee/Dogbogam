package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRecordSpringDataRepository extends JpaRepository<InsuranceRecordEntity, Long> {
    Optional<InsuranceRecordEntity> findByInsurance_InsuranceIdAndDog_DogId(Long insuranceId, Long dogId);
    Page<InsuranceRecordEntity> findAllByDog_DogIdOrderByRegistDate(Long dogId, Pageable pageable);
}
