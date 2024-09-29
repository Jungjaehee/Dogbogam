package com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HealthProblemResponse {
    private Long healthProblemId;
    private String problem;
}
