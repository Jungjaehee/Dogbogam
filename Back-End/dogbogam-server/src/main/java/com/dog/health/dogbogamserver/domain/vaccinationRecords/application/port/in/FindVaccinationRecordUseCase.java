package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;

public interface FindVaccinationRecordUseCase {
    VaccinationRecord findVaccinationRecordById(Long vaccinationRecordId);
}
