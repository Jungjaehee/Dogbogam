package com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto;

public enum DiagnosisItem {
    
    눈("염증"), 피부("피부 질환"), 비만("보행 이상");

    private String benefitCategory;

    DiagnosisItem(String benefitCategory) {
        this.benefitCategory = benefitCategory;
    }

    public String getBenefitCategory() {
        return benefitCategory;
    }
}
