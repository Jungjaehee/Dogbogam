package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;

import java.util.List;

public interface FindVaccinationRecordsUseCase {
    List<VaccinationRecord> findVaccinationsByDogId(Long dogId);
}
