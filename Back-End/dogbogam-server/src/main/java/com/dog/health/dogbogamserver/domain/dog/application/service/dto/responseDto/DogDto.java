package com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DogDto {
    Long dogId;
    String dogName;
    String imageUrl;
}
