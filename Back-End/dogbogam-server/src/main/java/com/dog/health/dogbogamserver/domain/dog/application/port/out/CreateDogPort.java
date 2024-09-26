package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;

public interface CreateDogPort {
    void save(CreateDogDTO createDogDTO);
}
