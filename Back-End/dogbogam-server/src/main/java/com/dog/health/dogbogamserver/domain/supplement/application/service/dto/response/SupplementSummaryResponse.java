package com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SupplementSummaryResponse {

    private Long supplementId;
    private String productName;
    private String imageUrl;
    private String offer;
    private Integer price;

    @Builder
    public SupplementSummaryResponse(Long supplementId, String productName, String imageUrl, String offer, Integer price) {
        this.supplementId = supplementId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.offer = offer;
        this.price = price;
    }

    public static SupplementSummaryResponse from(Long supplementId, String productName, String imageUrl, String offer, Integer price) {
        return SupplementSummaryResponse.builder()
                .supplementId(supplementId)
                .productName(productName)
                .imageUrl(imageUrl)
                .offer(offer)
                .price(price)
                .build();
    }
}

