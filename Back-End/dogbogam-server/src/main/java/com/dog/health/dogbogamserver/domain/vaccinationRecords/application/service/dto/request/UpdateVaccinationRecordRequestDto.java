package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UpdateVaccinationRecordRequestDto {
    @NotBlank(message = "진료 기록 아이디는 필수 입니다.")
    Long vaccinationRecordId;

    @NotBlank(message = "반려견 아이디는 필수 입니다.")
    Long dogId;

    @NotBlank(message = "진료 날짜는 필수 입니다.")
    LocalDateTime recordTime;
    String content;

    @NotBlank(message = "병원 이름은 필수 입니다.")
    String hospital;

    @NotBlank(message = "예방 접종 차수는 필수 입니다.")
    int vaccinationRound;

    @NotBlank(message = "비용은 필수 입니다.")
    Long cost;

    // File
}
