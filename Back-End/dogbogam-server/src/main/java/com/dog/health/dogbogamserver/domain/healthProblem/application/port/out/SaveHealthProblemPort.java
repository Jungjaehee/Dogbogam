package com.dog.health.dogbogamserver.domain.healthProblem.application.port.out;

import com.dog.health.dogbogamserver.domain.healthProblem.domain.HealthProblem;

public interface SaveHealthProblemPort {
    void saveHealthProblem(HealthProblem healthProblem);
}