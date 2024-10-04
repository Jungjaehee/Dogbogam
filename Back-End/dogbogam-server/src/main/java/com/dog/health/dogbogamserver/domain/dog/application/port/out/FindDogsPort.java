package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.List;

public interface FindDogsPort {
    List<Dog> findDogsByMemberId(Long memberId);
}
