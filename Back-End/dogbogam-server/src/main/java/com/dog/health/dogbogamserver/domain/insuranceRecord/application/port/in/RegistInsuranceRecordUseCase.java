package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistRequestDto;

public interface RegistInsuranceRecordUseCase {

    void registInsuranceRecord(RegistRequestDto registRequestDto);

}
