package com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplementSummaryResponse {
    private Long supplementId;
    private String productName;
    private String imageUrl;
    private Integer price;
}
