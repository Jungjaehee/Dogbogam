package com.dog.health.dogbogamserver.domain.insurances.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Insurance {
    private Long insuranceId;
    private String name;
    private String company;
    private String minAge;
    private String maxAge;
    private String fee;
    private String limit;
    private String period;
    private String description;
    private String coverageRatio;
    private String s3ImageName;
    private String s3ImageUrl;

    @Builder
    public Insurance(Long insuranceId, String name, String company, String minAge, String maxAge, String fee, String limit,
                     String period, String description, String coverageRatio, String s3ImageName, String s3ImageUrl) {
        this.insuranceId = insuranceId;
        this.name = name;
        this.company = company;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.fee = fee;
        this.limit = limit;
        this.period = period;
        this.description = description;
        this.coverageRatio = coverageRatio;
        this.s3ImageName = s3ImageName;
        this.s3ImageUrl = s3ImageUrl;
    }
}
