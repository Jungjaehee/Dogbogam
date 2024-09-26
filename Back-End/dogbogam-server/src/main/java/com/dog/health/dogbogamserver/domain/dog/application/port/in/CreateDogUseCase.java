package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

public interface CreateDogUseCase {
    void createDog(CreateDogDTO createDogDTO);
}
