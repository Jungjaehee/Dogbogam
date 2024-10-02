package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.UpdateDogRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateDogUseCase {
    void updateDog(UpdateDogRequestDTO updateDogRequestDTO, Long memberId);
}
