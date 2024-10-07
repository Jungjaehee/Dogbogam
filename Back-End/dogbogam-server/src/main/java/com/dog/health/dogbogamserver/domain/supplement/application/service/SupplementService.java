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

import java.util.*;
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

    @Override
    public SupplementSummaryResponse recommendSupplements(List<String> healthProblems) {
        List<String> mappedOffers = healthProblems.stream()
                .map(healthProblemToOfferMap::get)
                .collect(Collectors.toList());

        // 페이지 및 사이즈를 적용하여 영양제 로드
        List<Supplement> supplements = loadSupplementPort.loadAllSupplements(0, Integer.MAX_VALUE);

        // 중복을 피하기 위해 Set 사용
        Set<Supplement> filteredSupplements = supplements.stream()
                .filter(supplement -> matchesAnyOffer(supplement.getOffer(), mappedOffers))
                .collect(Collectors.toSet());

        // 무작위로 1개의 영양제 선택
        if (filteredSupplements.isEmpty()) {
            return null; // 조건에 맞는 영양제가 없을 경우 null 반환
        }
        Supplement randomSupplement = filteredSupplements.stream()
                .skip(new Random().nextInt(filteredSupplements.size()))
                .findFirst()
                .orElseThrow();

        return SupplementSummaryResponse.builder()
                .supplementId(randomSupplement.getSupplementId())
                .productName(randomSupplement.getProductName())
                .imageUrl(randomSupplement.getImageUrl())
                .offer(randomSupplement.getOffer())
                .price(randomSupplement.getPrice())
                .build();
    }

    private final Map<String, String> healthProblemToOfferMap = Map.of(
            "눈", "눈건강",
            "관절", "관절강화",
            "면역력", "면역력강화",
            "피모", "피모관리",
            "구강", "구강관리",
            "비만", "체중유지",
            "심장", "심장건강",
            "변비", "소화개선",
            "스트레스", "스트레스완화"
    );

    // offer에 하나라도 매칭되는 항목이 있는지 확인하는 메서드
    private boolean matchesAnyOffer(String offer, List<String> mappedOffers) {
        List<String> offerList = List.of(offer.split(", ")); // 쉼표로 나누어 리스트로 변환
        return mappedOffers.stream().anyMatch(offerList::contains); // 하나라도 매칭되는지 확인
    }

}
