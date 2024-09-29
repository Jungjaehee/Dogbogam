package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.List;
import java.util.Optional;

public interface FindDogsPort {
    Optional<List<Dog>> findDogsByMemberId(Long memberId);
}
