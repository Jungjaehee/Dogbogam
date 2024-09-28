package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.UpdateVaccinationRecordRequestDto;

public interface UpdateVaccinationRecordUseCase {
    void updateVaccinationRecord(UpdateVaccinationRecordRequestDto updateVaccinationRecordRequestDto);
}
