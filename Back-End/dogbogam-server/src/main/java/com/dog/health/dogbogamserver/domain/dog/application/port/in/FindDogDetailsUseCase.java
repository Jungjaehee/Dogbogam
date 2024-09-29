package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.Optional;

public interface FindDogDetailsUseCase {
    Optional<Dog> findDogDetails(Long dogId);
}
