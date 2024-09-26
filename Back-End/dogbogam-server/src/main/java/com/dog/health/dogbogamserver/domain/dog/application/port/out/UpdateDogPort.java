package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

public interface UpdateDogPort {
    void update(Dog dog);
}
