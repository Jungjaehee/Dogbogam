package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.UpdateDogDTO;

public interface UpdateDogUseCase {
    void updateDog(UpdateDogDTO updateDogDTO, Long memberId);
}
