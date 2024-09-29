package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;

public interface CreateDogUseCase {
    void createDog(CreateDogDTO createDogDTO, Long memberId);
}
