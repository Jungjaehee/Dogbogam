package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.CreateVaccinationRecordRequestDto;

public interface CreateVaccinationRecordUseCase {
    void createVaccinationRecord(CreateVaccinationRecordRequestDto createVaccinationRecordRequestDto);
}
