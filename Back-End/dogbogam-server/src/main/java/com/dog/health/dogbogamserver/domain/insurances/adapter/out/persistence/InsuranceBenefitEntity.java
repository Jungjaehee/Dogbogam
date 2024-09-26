package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance_benefit")
@Getter
@NoArgsConstructor
public class InsuranceBenefitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_benefit_id")
    private Long insuranceBenefitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", nullable = false)
    private InsuranceEntity insurance;

    @Column(name = "benefit", nullable = false)
    private String benefit;
}
