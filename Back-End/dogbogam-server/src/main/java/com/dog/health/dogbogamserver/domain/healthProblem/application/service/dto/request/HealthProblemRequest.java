package com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HealthProblemRequest {

    @NotNull(message = "반려견 ID는 필수입니다.")
    private Long dogId;

    @NotEmpty(message = "건강 고민은 최소 하나 이상 입력해야 합니다.")
    private List<String> problem;
}
