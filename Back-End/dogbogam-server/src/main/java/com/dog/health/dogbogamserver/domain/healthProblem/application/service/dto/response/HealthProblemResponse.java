package com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HealthProblemResponse {
    private String problem;

    @Builder
    public HealthProblemResponse(String problem) {
        this.problem = problem;
    }
}
