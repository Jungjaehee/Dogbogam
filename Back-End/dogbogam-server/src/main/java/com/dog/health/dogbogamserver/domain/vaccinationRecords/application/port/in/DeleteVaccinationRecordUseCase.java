package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import java.io.IOException;

public interface DeleteVaccinationRecordUseCase {
    void deleteVaccinationRecord(Long vaccinationRecordId) throws IOException;
}
