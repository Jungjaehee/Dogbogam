package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;

import java.util.Optional;

public interface FindVaccinationRecordPort {
    Optional<VaccinationRecord> findVaccinationRecordById(Long vaccinationRecordId);
}
