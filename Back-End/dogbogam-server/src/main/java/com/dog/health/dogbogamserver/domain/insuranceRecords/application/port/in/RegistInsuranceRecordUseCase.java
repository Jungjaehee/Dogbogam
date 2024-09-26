package com.dog.health.dogbogamserver.domain.insuranceRecords.application.port.in;

import com.dog.health.dogbogamserver.domain.insuranceRecords.adapter.in.dto.RegistRequestDto;

public interface RegistInsuranceRecordUseCase {

    void registInsuranceRecord(RegistRequestDto registRequestDto);

}
