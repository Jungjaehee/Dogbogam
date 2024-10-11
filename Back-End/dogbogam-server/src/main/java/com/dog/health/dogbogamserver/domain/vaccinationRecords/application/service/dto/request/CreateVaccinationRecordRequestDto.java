package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVaccinationRecordRequestDto {
    @NotNull(message = "반려견 아이디는 필수 입니다.")
    Long dogId;

    @NotNull(message = "진료 날짜는 필수 입니다.")
    LocalDateTime recordTime;

    String content;

    @NotBlank(message = "병원 이름은 필수 입니다.")
    String hospital;

    @NotNull(message = "비용은 필수 입니다.")
    Long cost;

    @NotNull(message = "예방 접종 차수는 필수 입니다.")
    Integer vaccinationRound;

    // File
    MultipartFile image;
}
