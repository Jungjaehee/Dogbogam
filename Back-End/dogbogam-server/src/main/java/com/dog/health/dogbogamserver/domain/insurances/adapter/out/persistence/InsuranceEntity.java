package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance")
@NoArgsConstructor
@Getter
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long insuranceId;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "min_age")
    private int minAge;

    @Column(name = "max_age")
    private int maxAge;

    @Column(name = "fee")
    private int fee;

    @Column(name = "limit")
    private int limit;

    @Column(name = "period")
    private String period;

    @Column(name = "description")
    private String description;

    @Column(name = "coverage_ratio")
    private String coverageRatio;

}
