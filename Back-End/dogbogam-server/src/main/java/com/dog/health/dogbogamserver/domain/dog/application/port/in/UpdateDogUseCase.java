package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

public interface UpdateDogUseCase {
    void updateDog(Long id, Dog dog);
}
