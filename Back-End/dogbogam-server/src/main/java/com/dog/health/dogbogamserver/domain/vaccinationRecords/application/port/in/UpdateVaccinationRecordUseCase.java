package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.UpdateVaccinationRecordRequestDto;

import java.io.IOException;

public interface UpdateVaccinationRecordUseCase {
    void updateVaccinationRecord(UpdateVaccinationRecordRequestDto updateVaccinationRecordRequestDto) throws IOException;
}
