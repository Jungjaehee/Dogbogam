package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;

import java.util.List;
import java.util.Optional;

public interface FindVaccinationRecordsPort {
    List<VaccinationRecord> findVaccinationRecordsByDogId(Long dogId);
}
