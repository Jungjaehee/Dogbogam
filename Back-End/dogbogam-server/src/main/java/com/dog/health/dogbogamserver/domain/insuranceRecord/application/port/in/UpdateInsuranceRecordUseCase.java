package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;


import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateInsuranceRecordRequestDto;

public interface UpdateInsuranceRecordUseCase {

    void updateInsuranceRecord(Long memberId, UpdateInsuranceRecordRequestDto updateRequestDto);

}
