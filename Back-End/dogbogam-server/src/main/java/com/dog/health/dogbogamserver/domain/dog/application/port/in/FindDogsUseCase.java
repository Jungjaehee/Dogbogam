package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.FindDogsDogInfoDto;

import java.util.List;
import java.util.Optional;

public interface FindDogsUseCase {
    Optional<List<FindDogsDogInfoDto>> findDogsByMemberId(Long memberId);
}
