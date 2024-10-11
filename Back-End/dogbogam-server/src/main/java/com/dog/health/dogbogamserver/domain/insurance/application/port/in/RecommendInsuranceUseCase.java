package com.dog.health.dogbogamserver.domain.insurance.application.port.in;

import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.DiagnosisItem;
import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.RecommendInsuranceResponseDto;

public interface RecommendInsuranceUseCase {
    RecommendInsuranceResponseDto recommendInsurance(DiagnosisItem diagnosisItem);
}
