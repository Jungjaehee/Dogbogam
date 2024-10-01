package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.CreateDogRequestDTO;

public interface CreateDogUseCase {
    void createDog(CreateDogRequestDTO createDogRequestDTO, Long memberId);
}
