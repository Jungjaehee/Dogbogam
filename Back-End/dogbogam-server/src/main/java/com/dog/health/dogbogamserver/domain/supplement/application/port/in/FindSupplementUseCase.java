package com.dog.health.dogbogamserver.domain.supplement.application.port.in;

import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementDetailResponse;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementSummaryResponse;

import java.util.List;

public interface FindSupplementUseCase {
    List<SupplementSummaryResponse> findAllSupplements(int page, int size);
    SupplementDetailResponse findSupplementById(Long supplementId);
}
