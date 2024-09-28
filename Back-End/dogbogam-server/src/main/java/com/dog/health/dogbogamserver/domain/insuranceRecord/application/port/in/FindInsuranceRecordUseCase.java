package com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.InsuranceRecordResponseDto;

public interface FindInsuranceRecordUseCase {

    InsuranceRecordResponseDto findInsuranceRecordById(Long insuranceRecordId);

}
