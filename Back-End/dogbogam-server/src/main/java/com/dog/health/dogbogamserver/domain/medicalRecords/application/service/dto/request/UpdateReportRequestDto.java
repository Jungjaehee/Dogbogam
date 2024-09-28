package com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class UpdateReportRequestDto {
    @NotNull(message = "진료 기록 아이디는 필수 입니다.")
    Long reportId;

    @NotNull(message = "반려견 아이디는 필수 입니다.")
    Long dogId;

    @NotNull(message = "진료 날짜는 필수 입니다.")
    LocalDate recordDate;
    String content;

    @NotNull(message = "병원 이름은 필수 입니다.")
    String hospital;
    // File
}
