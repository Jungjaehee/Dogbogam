package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.UpdateDogRequestDTO;

public interface UpdateDogUseCase {
    void updateDog(UpdateDogRequestDTO updateDogRequestDTO, Long memberId);
}
