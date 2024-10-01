package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.FindDogsResponseDto;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

import java.util.List;
import java.util.Optional;

public interface FindDogsUseCase {
    Optional<FindDogsResponseDto> findDogsByMemberId(Long memberId, int page, int size);
}
