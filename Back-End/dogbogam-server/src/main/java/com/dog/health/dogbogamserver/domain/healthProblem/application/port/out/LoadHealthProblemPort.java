package com.dog.health.dogbogamserver.domain.healthProblem.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.HealthProblem;

import java.util.List;
import java.util.Optional;

public interface LoadHealthProblemPort {
    List<HealthProblem> loadHealthProblemsByDogId(Long dogId);
    Optional<Dog> loadDogById(Long dogId);  // 반려견 정보를 로드하기 위한 포트
}