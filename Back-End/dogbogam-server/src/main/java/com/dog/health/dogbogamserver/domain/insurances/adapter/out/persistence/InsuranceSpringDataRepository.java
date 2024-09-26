package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceSpringDataRepository extends JpaRepository<InsuranceEntity, Long> {

    Optional<InsuranceEntity> findByInsuranceId(Long insuranceId);
}
