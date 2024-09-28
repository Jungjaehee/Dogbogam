package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.out.persistence.VaccinationRecordEntity;

public interface CreateVaccinationRecordPort {
    void createVaccinationRecord(VaccinationRecordEntity vaccinationRecordEntity);
}
