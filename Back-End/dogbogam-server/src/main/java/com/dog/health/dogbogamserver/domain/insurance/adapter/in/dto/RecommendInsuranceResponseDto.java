package com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RecommendInsuranceResponseDto {

    private Long insuranceId;

    private String name;

    private String company;

    private String image;

    private String fee;
}
