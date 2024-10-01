package com.dog.health.dogbogamserver.domain.healthProblem.domain;

public enum ProblemType {
    눈, 관절, 면역력강화, 피모, 구강, 비만, 심장, 변비, 스트레스;

    public static boolean isValid(String problem) {
        for (ProblemType p : values()) {
            if (p.name().equals(problem)) {
                return true;
            }
        }
        return false;
    }
}
