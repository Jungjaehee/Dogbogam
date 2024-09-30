package com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HealthProblemResponse {
    private Long healthProblemId;
    private String problem;

    @Builder
    public HealthProblemResponse(Long healthProblemId, String problem) {
        this.healthProblemId = healthProblemId;
        this.problem = problem;
    }
}
