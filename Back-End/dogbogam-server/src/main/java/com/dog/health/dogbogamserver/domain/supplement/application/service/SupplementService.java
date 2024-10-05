package com.dog.health.dogbogamserver.domain.supplement.application.service;

import com.dog.health.dogbogamserver.domain.supplement.application.port.in.FindSupplementsUseCase;
import com.dog.health.dogbogamserver.domain.supplement.application.port.out.LoadSupplementPort;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementSummaryResponse;
import com.dog.health.dogbogamserver.domain.supplement.domain.Supplement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplementService implements FindSupplementsUseCase {

    private final LoadSupplementPort loadSupplementPort;

    @Override
    public List<SupplementSummaryResponse> findAllSupplements(int page, int size) {
        List<Supplement> supplements = loadSupplementPort.loadAllSupplements(page, size);
        return supplements.stream()
                .map(supplement -> SupplementSummaryResponse.builder()
                        .supplementId(supplement.getSupplementId())
                        .productName(supplement.getProductName())
                        .imageUrl(supplement.getImageUrl())
                        .price(supplement.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
