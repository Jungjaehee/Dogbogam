package com.dog.health.dogbogamserver.domain.insurances.domain;

import lombok.Getter;

@Getter
public class InsuranceBenefit {
    public enum BenefitType {
        관절, 염증, 보행, 구강질환, 피부, 탈장, 의료비, 보상책임, 장례
    }

    private Long insuranceBenefitId;
    private Insurance insurance;
    private BenefitType benefit;
}
