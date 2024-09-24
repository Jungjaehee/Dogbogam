package com.dog.health.dogbogamserver.domain.healthProblems.domain;

import com.dog.health.dogbogamserver.domain.dogs.domain.Dog;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HealthProblem {
    private Long healthProblemId;
    private Dog dog;
    private String problem;

    @Builder
    public HealthProblem(Long healthProblemId, Dog dog, String problem) {
        this.healthProblemId = healthProblemId;
        this.dog = dog;
        this.problem = problem;
    }
}
