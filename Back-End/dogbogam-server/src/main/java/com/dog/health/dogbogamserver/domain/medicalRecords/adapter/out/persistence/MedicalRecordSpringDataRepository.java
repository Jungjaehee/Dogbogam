package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalRecordSpringDataRepository extends JpaRepository<MedicalRecordEntity, Long> {
    List<MedicalRecordEntity> findAllByDog_DogId(Long dogId);
}