package com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HealthProblemRequest {

    @NotNull(message = "반려견 ID는 필수입니다.")
    private Long dogId;

    @NotBlank(message = "건강 고민은 필수입니다.")
    private String problem;
}
