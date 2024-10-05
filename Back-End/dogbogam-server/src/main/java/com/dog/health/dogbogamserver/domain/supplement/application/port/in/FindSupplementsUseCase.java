package com.dog.health.dogbogamserver.domain.supplement.application.port.in;

import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementSummaryResponse;

import java.util.List;

public interface FindSupplementsUseCase {
    List<SupplementSummaryResponse> findAllSupplements(int page, int size);
}
