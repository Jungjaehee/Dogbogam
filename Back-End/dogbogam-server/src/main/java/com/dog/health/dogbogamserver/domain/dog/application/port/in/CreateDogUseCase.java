package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.CreateDogRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CreateDogUseCase {
    void createDog(CreateDogRequestDTO createDogRequestDTO, Long memberId, MultipartFile image);
}
