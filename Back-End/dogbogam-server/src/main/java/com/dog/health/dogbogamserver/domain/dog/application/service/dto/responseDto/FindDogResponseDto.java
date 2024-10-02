package com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto;

import com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response.HealthProblemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FindDogResponseDto {
    Long dogId;
    String name;
    String breed;
    String gender;
    LocalDate birth;
    double weight;
    boolean isNeutered;
    String imageUrl;
    List<HealthProblemResponse> healthProblems;
}

