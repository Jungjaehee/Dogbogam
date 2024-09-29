package com.dog.health.dogbogamserver.domain.healthProblem.application.port.in;

public interface CreateHealthProblemUseCase {
    void createHealthProblems(Long dogId, String problems);
}

