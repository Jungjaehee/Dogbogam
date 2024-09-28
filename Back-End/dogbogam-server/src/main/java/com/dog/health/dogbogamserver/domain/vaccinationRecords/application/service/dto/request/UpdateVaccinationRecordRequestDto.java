package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UpdateVaccinationRecordRequestDto {
    @NotNull(message = "진료 기록 아이디는 필수 입니다.")
    Long vaccinationRecordId;

    @NotNull(message = "반려견 아이디는 필수 입니다.")
    Long dogId;

    @NotNull(message = "진료 날짜는 필수 입니다.")
    LocalDateTime recordTime;
    String content;

    @NotNull(message = "병원 이름은 필수 입니다.")
    String hospital;

    @NotNull(message = "예방 접종 차수는 필수 입니다.")
    int vaccinationRound;

    // File
}
