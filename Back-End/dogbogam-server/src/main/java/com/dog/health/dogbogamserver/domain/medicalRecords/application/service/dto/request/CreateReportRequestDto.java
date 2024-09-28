package com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateReportRequestDto {
    @NotNull(message = "반려견 아이디는 필수 입니다.")
    Long dogId;

    @NotNull(message = "진료 날짜는 필수 입니다.")
    LocalDateTime recordTime;
    String content;

    @NotNull(message = "병원 이름은 필수 입니다.")
    String hospital;

    @NotNull(message = "비용은 필수 입니다.")
    Long cost;

    // File
}
