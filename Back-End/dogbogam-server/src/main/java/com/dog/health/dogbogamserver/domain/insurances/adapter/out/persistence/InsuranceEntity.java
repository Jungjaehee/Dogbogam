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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "min_age", nullable = false)
    private int minAge;

    @Column(name = "max_age", nullable = false)
    private int maxAge;

    @Column(name = "fee", nullable = false)
    private int fee;

    @Column(name = "limit", nullable = false)
    private int limit;

    @Column(name = "period", nullable = false)
    private String period;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "coverage_ratio", nullable = false)
    private String coverageRatio;

}
