package com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRecordSpringDataRepository extends JpaRepository<VaccinationRecordEntity, Long> {
    List<VaccinationRecordEntity> findAllByDog_DogId(Long dogId);
}
