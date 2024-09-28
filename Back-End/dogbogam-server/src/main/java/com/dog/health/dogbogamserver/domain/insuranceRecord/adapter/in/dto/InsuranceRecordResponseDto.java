package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class InsuranceRecordResponseDto {

    private Long insuranceRecordId;

    private Long dogId;

    private String dogName;

    private Long insuranceId;

    private String insuranceName;

    private String insuranceCompany;

    private String insuranceImage;

    private LocalDate registDate;

    private Long monthlyPayment;

    private LocalDate expirationDate;

}
