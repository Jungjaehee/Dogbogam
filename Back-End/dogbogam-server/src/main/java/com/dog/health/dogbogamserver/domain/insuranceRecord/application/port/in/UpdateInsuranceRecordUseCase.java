package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;


import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateRequestDto;

public interface UpdateInsuranceRecordUseCase {

    void updateInsuranceRecord(UpdateRequestDto updateRequestDto);

}
