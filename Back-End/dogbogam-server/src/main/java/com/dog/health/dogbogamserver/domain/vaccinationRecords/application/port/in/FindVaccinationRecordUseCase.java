package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.response.FindVaccinationRecordResponseDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;

public interface FindVaccinationRecordUseCase {
    FindVaccinationRecordResponseDto findVaccinationRecordById(Long vaccinationRecordId);
}
