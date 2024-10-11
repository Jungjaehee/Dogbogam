package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.CreateDogRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CreateDogUseCase {
    Map<String, Long> createDog(CreateDogRequestDTO createDogRequestDTO, Long memberId) throws IOException;
}
