package com.dog.health.dogbogamserver.domain.insurances.domain;

import lombok.Getter;

@Getter
public class InsuranceBenefit {
    private Long insuranceBenefitId;
    private Insurance insurance;
    private String benefit;
}
