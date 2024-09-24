package com.dog.health.dogbogamserver.domain.insurances.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsuranceBenefit {
    private Long insuranceBenefitId;
    private Insurance insurance;
    private String benefit;
}
