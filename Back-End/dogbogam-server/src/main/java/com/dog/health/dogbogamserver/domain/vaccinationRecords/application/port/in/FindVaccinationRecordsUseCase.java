package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.response.FindVaccinationRecordResponseDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;

import java.util.List;

public interface FindVaccinationRecordsUseCase {
    List<FindVaccinationRecordResponseDto> findVaccinationsByDogId(Long dogId);
}
