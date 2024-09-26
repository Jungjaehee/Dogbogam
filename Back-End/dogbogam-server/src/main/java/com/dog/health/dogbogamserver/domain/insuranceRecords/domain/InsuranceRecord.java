package com.dog.health.dogbogamserver.domain.insuranceRecords.domain;

import com.dog.health.dogbogamserver.domain.dogs.domain.Dog;
import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
