package com.dog.health.dogbogamserver.domain.insurance.application.port.in;

import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.DiagnosisItem;
import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.RecommandInsuranceResponseDto;

public interface RecommandInsuranceUseCase {
    RecommandInsuranceResponseDto recommandInsurance(DiagnosisItem diagnosisItem);
}
