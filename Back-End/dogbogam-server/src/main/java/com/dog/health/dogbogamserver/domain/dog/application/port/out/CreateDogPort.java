package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;

public interface CreateDogPort {
    void save(Dog dog);
}
