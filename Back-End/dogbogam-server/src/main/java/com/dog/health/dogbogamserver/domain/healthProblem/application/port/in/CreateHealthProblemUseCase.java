package com.dog.health.dogbogamserver.domain.healthProblem.application.port.in;

import java.util.List;

public interface CreateHealthProblemUseCase {
    void createHealthProblems(Long dogId, List<String> problems);
}

