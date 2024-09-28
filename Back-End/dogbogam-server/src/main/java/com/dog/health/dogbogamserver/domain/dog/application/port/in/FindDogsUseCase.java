package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.List;
import java.util.Optional;

public interface FindDogsUseCase {
    Optional<List<Dog>> findDogsByMemberId(Long memberId);
}
