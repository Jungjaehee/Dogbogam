package com.dog.health.dogbogamserver.domain.insuranceRecord.domain;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceRecord {

    private Long insuranceRecordId;
    private Insurance insurance;
    private Dog dog;
    private LocalDate registDate;
    private Long monthlyPayment;
    private LocalDate expirationDate;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
