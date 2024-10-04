package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindVaccinationRecordResponseDto {
    Long dogId;
    LocalDateTime recordTime;
    String content;
    String hospital;
    Long cost;
    int vaccinationRound;
    String imageUrl;
}
