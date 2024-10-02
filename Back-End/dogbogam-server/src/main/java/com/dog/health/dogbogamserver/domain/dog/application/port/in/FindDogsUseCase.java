package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.DogDto;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.List;
import java.util.Optional;

public interface FindDogsUseCase {
    Optional<List<DogDto>> findDogsByMemberId(Long memberId);
}
