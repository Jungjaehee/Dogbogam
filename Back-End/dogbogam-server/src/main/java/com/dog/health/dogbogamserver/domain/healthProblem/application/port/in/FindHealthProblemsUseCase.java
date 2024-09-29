package com.dog.health.dogbogamserver.domain.healthProblem.application.port.in;

import com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response.HealthProblemResponse;

import java.util.List;

public interface FindHealthProblemsUseCase {
    List<HealthProblemResponse> findHealthProblems(Long dogId);
}
