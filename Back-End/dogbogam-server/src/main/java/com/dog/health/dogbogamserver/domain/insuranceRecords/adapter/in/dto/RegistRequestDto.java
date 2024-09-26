package com.dog.health.dogbogamserver.domain.insuranceRecords.adapter.in.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RegistRequestDto {

    @NotNull
    private Long insuranceId;

    @NotNull
    private Long dogId;

    @NotNull
    private LocalDate registDate;

    @NotNull
    private Long monthlyPayment;

    @NotNull
    private LocalDate expirationDate;

}
