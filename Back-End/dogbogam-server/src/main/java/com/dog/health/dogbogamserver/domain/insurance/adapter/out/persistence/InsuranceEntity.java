package com.dog.health.dogbogamserver.domain.insurance.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance")
@NoArgsConstructor
@Getter
@AllArgsConstructor
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
    private String minAge;

    @Column(name = "max_age", nullable = false)
    private String maxAge;

    @Column(name = "fee", nullable = false)
    private String fee;

    @Column(name = "limit_fee", nullable = false)
    private String limitFee;

    @Column(name = "period", nullable = false)
    private String period;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "coverage_ratio", nullable = false)
    private String coverageRatio;

    @Column(name = "s3_image_name")
    private String s3ImageName;

    @Column(name = "s3_image_url")
    private String s3ImageUrl;

}
