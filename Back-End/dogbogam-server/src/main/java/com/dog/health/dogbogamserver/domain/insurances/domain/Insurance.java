package com.dog.health.dogbogamserver.domain.insurances.domain;

import lombok.Getter;

@Getter
public class Insurance {
    private Long insuranceId;
    private String name;
    private String company;
    private int minAge;
    private int maxAge;
    private int fee;
    private int limit;
    private String period;
    private String description;
    private String coverage_ratio;

}
