package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceSpringDataRepository extends JpaRepository<InsuranceEntity, Long> {
}
