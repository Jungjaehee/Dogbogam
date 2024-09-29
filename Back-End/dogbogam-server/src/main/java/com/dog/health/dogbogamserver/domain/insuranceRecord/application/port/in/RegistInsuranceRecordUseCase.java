package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistInsuranceRecordRequestDto;

public interface RegistInsuranceRecordUseCase {

    void registInsuranceRecord(Long memberId, RegistInsuranceRecordRequestDto registRequestDto);

}
