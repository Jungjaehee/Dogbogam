package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogSpringDataRepository extends JpaRepository<DogEntity, Long> {
}
