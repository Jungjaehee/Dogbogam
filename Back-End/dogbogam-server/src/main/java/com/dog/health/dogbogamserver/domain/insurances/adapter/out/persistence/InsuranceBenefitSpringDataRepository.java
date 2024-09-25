package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceBenefitSpringDataRepository extends JpaRepository<InsuranceBenefitEntity, Long> {

    List<InsuranceBenefitEntity> findByBenefitIn(List<String> benefit);
}
