package com.dog.health.dogbogamserver.domain.insuranceRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dogs.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence.InsuranceEntity;
import com.dog.health.dogbogamserver.global.baseTimeEntity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "insurance_record")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceRecordEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", nullable = false)
    private InsuranceEntity insurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", nullable = false)
    private DogEntity dog;

    @Column(name = "regist_date", nullable = false)
    private LocalDate registDate;

    @Column(name = "monthly_payment", nullable = false)
    private Long monthlyPayment;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

}
