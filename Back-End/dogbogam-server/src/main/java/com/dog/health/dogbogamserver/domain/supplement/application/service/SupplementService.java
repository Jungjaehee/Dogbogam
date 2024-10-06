package com.dog.health.dogbogamserver.domain.supplement.application.service;

import com.dog.health.dogbogamserver.domain.supplement.application.port.in.FindSupplementUseCase;
import com.dog.health.dogbogamserver.domain.supplement.application.port.out.LoadSupplementPort;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementDetailResponse;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementSummaryResponse;
import com.dog.health.dogbogamserver.domain.supplement.domain.Supplement;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplementService implements FindSupplementUseCase {

    private final LoadSupplementPort loadSupplementPort;

    @Override
    public List<SupplementSummaryResponse> findAllSupplements(int page, int size) {
        List<Supplement> supplements = loadSupplementPort.loadAllSupplements(page, size);
        return supplements.stream()
                .map(supplement -> SupplementSummaryResponse.builder()
                        .supplementId(supplement.getSupplementId())
                        .productName(supplement.getProductName())
                        .imageUrl(supplement.getImageUrl())
                        .offer(supplement.getOffer())
                        .price(supplement.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public SupplementDetailResponse findSupplementById(Long supplementId) {
        Supplement supplement = loadSupplementPort.findSupplementById(supplementId)
                .orElseThrow(() -> new CustomException(ErrorCode.SUPPLEMENT_NOT_FOUND));

        return SupplementDetailResponse.builder()
                .supplementId(supplement.getSupplementId())
                .productName(supplement.getProductName())
                .brandName(supplement.getBrandName())
                .target(supplement.getTarget())
                .how(supplement.getHow())
                .offer(supplement.getOffer())
                .type(supplement.getType())
                .basis(supplement.getBasis())
                .protein(supplement.getProtein())
                .fat(supplement.getFat())
                .feature(supplement.getFeature())
                .price(supplement.getPrice())
                .imageName(supplement.getImageName())
                .imageUrl(supplement.getImageUrl())
                .build();
    }

}
