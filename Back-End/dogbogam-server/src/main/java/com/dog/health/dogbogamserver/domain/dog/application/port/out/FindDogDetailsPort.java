package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.Optional;

public interface FindDogDetailsPort {
    Optional<Dog> findByDogId(Long Dogid);
}
