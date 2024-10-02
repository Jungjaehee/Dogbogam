package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.FindDogResponseDto;

public interface FindDogDetailsUseCase {
    FindDogResponseDto findDogDetails(Long dogId);
}
