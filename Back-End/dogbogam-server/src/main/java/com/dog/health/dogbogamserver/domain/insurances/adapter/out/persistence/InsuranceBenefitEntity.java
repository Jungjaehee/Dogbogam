package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;
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
    @JoinColumn(name = "insurance_id")
    private InsuranceEntity insurance;

    @Column(name = "benefit")
    private String benefit;
}
