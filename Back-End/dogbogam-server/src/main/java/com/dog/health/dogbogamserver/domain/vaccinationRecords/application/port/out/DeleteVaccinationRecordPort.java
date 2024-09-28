package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out;

public interface DeleteVaccinationRecordPort {
    void deleteVaccinationRecord(Long vaccinationRecordId);
}
