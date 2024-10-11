package com.dog.health.dogbogamserver.domain.vaccinationRecords.domain;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class VaccinationRecord {
    private Long vaccinationRecordId;
    private Dog dog;
    private LocalDateTime recordTime;
    private String content;
    private String hospital;
    private Long cost;
    private int vaccinationRound;
    private String imageName;
    private String imageUrl;
}
