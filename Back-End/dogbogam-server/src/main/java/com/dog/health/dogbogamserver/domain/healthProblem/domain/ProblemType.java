package com.dog.health.dogbogamserver.domain.healthProblem.domain;

public enum ProblemType {
    눈건강, 피모관리, 면역력강화, 식욕증진, 구강관리, 스트레스완화, 분리불안해소, 체중유지, 소화개선, 심장건강, 관절강화;

    public static boolean isValid(String problem) {
        for (ProblemType p : values()) {
            if (p.name().equals(problem)) {
                return true;
            }
        }
        return false;
    }
}
