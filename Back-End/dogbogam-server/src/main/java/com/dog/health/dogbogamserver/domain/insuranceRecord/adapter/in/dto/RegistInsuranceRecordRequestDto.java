package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RegistInsuranceRecordRequestDto {

    @NotNull(message = "보험 Id가 비어있습니다.")
    private Long insuranceId;

    @NotNull(message = "반려견 Id가 비어있습니다.")
    private Long dogId;


    @NotNull(message = "보험 등록 날짜가 비어있습니다.")
    @PastOrPresent(message = "보험 등록 날짜는 오늘 이전만 가능합니다.")
    private LocalDate registDate;

    @NotNull(message = "월 납입료가 비어있습니다.")
    @Positive(message = "월 납입료 0원 이하는 불가능합니다.")
    private Long monthlyPayment;

    @NotNull(message = "보험 만료 기간이 비어있습니다.")
    @FutureOrPresent(message = "보험 만료 날짜는 오늘 이후만 가능합니다.")
    private LocalDate expirationDate;

}
