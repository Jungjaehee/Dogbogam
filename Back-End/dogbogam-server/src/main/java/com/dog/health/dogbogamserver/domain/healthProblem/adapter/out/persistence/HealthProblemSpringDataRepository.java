package com.dog.health.dogbogamserver.domain.healthProblem.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthProblemSpringDataRepository extends JpaRepository<HealthProblemEntity, Long> {
    List<HealthProblemEntity> findByDog_DogId(Long dogId);
}
